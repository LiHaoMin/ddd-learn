package com.github.lihaomin.ddd.domain.service.impl;

import com.github.lihaomin.ddd.bank.types.ExchangeRate;
import com.github.lihaomin.ddd.bank.types.Money;
import com.github.lihaomin.ddd.domain.entity.Account;
import com.github.lihaomin.ddd.domain.service.AccountTransferService;
import org.springframework.stereotype.Component;

/**
 * @author lihaomin
 * @date 2021/11/23
 */
@Component
public class AccountTransferServiceImpl implements AccountTransferService {

    @Override
    public void transfer(Account sourceAccount, Account targetAccount, Money targetMoney, ExchangeRate exchangeRate) {
        Money sourceMoney = exchangeRate.exchangeTo(targetMoney);
        sourceAccount.deposit(sourceMoney);
        targetAccount.withdraw(targetMoney);
    }
}
