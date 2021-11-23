package com.github.lihaomin.ddd.domain.service;

import com.github.lihaomin.ddd.bank.types.ExchangeRate;
import com.github.lihaomin.ddd.bank.types.Money;
import com.github.lihaomin.ddd.domain.entity.Account;

/**
 * @author lihaomin
 * @date 2021/11/23
 */
public interface AccountTransferService {
    void transfer(Account sourceAccount, Account targetAccount, Money targetMoney, ExchangeRate exchangeRate);
}
