package com.github.lihaomin.ddd.bank.service.impl;

import com.github.lihaomin.ddd.bank.persistence.dao.AccountDAO;
import com.github.lihaomin.ddd.bank.persistence.model.AccountDO;
import com.github.lihaomin.ddd.bank.service.TransferService;
import com.github.lihaomin.ddd.bank.service.YahooForexService;
import com.github.lihaomin.ddd.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author lihaomin
 * @date 2021/11/23
 */
@Deprecated
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {
    private final AccountDAO accountDAO;
    private final YahooForexService yahooForexService;

    private final AccountRepository accountRepository;

    @Override
    public ResponseEntity<Boolean> transfer(Long sourceUserId, String targetAccountNumber, BigDecimal targetAmount, String targetCurrency) {
        // 1. 查询原账户、目标账户
        AccountDO sourceAccountDO = accountDAO.getOne(sourceUserId);
        AccountDO targetAccountDO = accountDAO.getOne(null);

        // 2. 业务参数校验
        if (!targetAccountDO.getCurrency().equals(targetCurrency)) {
            throw new RuntimeException("目标币种不一致");
        }

        // 3. 币种不一致,从外部API获取税率
        BigDecimal exchangeRate = BigDecimal.ONE;
        if (!sourceAccountDO.getCurrency().equals(targetCurrency)) {
            exchangeRate = yahooForexService.getExchangeRate(sourceAccountDO.getCurrency(), targetCurrency);
        }
        BigDecimal sourceAmount = targetAmount.divide(exchangeRate, RoundingMode.DOWN);

        // 4. 业务参数校验
        if (sourceAccountDO.getAvailable().compareTo(sourceAmount) < 0) {
            throw new RuntimeException("金额不足");
        }
        if (sourceAccountDO.getDailyLimit().compareTo(sourceAmount) < 0) {
            throw new RuntimeException("超过日上限");
        }

        // 5. 计算新值，并且更新字段
        BigDecimal newSource = sourceAccountDO.getAvailable().subtract(sourceAmount);
        BigDecimal newTarget = targetAccountDO.getAvailable().add(targetAmount);
        sourceAccountDO.setAvailable(newSource);
        targetAccountDO.setAvailable(newTarget);

        // 6. 更新到数据库
        accountDAO.save(sourceAccountDO);
        accountDAO.save(targetAccountDO);

        // 7. 发送审计消息 TODO kafka

        return ResponseEntity.ok(true);
    }
}
