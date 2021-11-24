package com.github.lihaomin.ddd.bank.service.impl;

import com.github.lihaomin.ddd.bank.service.TransferService;
import com.github.lihaomin.ddd.bank.types.AccountNumber;
import com.github.lihaomin.ddd.bank.types.ExchangeRate;
import com.github.lihaomin.ddd.bank.types.Money;
import com.github.lihaomin.ddd.bank.types.UserId;
import com.github.lihaomin.ddd.domain.entity.Account;
import com.github.lihaomin.ddd.domain.service.AccountTransferService;
import com.github.lihaomin.ddd.domain.types.AuditMessage;
import com.github.lihaomin.ddd.external.ExchangeRateService;
import com.github.lihaomin.ddd.messaging.AuditMessageProducer;
import com.github.lihaomin.ddd.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author lihaomin
 * @date 2021/11/23
 */
@Service
@RequiredArgsConstructor
public class NewTransferServiceImpl implements TransferService {
    private final AccountRepository accountRepository;
    private final ExchangeRateService exchangeRateService;
    private final AuditMessageProducer auditMessageProducer;
    private final AccountTransferService accountTransferService;

    @Override
    public ResponseEntity<Boolean> transfer(Long sourceUserId, String targetAccountNumber, BigDecimal targetAmount, String targetCurrency) {
        // 1. 查询原账户、目标账户
        Account sourceAccount = accountRepository.find(new UserId(sourceUserId));
        Account targetAccount = accountRepository.find(new AccountNumber(targetAccountNumber));
        // 2. 业务参数校验
        // 3. 币种不一致,从外部API获取税率
        ExchangeRate exchangeRate = exchangeRateService.getExchangeRate(sourceAccount.getCurrency(), targetAccount.getCurrency());
        Money targetMoney = new Money(targetAmount, targetAccount.getCurrency());
//        Money sourceMoney = exchangeRate.exchangeTo(targetMoney);
//        // 4. 业务参数校验
//        // 5. 计算新值，并且更新字段
//        sourceAccount.deposit(sourceMoney);
//        targetAccount.withdraw(targetMoney);
        accountTransferService.transfer(sourceAccount, targetAccount, targetMoney, exchangeRate);
        // 6. 更新到数据库
        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);
        // 7. 发送审计消息
        AuditMessage auditMessage = new AuditMessage(sourceAccount, targetAccount, targetMoney);
        auditMessageProducer.send(auditMessage);
        return ResponseEntity.ok(true);
    }
}
