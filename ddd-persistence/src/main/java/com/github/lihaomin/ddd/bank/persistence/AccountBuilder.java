package com.github.lihaomin.ddd.bank.persistence;

import com.github.lihaomin.ddd.bank.persistence.model.AccountDO;
import com.github.lihaomin.ddd.bank.types.AccountId;
import com.github.lihaomin.ddd.bank.types.AccountNumber;
import com.github.lihaomin.ddd.bank.types.Currency;
import com.github.lihaomin.ddd.bank.types.Money;
import com.github.lihaomin.ddd.bank.types.UserId;
import com.github.lihaomin.ddd.domain.entity.Account;

/**
 * @author lihaomin
 * @date 2021/11/23
 */
public class AccountBuilder {
    public static Account toAccount(AccountDO accountDO) {
        Currency currency = new Currency(accountDO.getCurrency());
        return new Account(new AccountId(accountDO.getId()), new AccountNumber(accountDO.getAccountNumber()),
                new UserId(accountDO.getId()), new Money(accountDO.getAvailable(), currency),
                new Money(accountDO.getDailyLimit(), currency));
    }

    public static AccountDO fromAccount(Account account) {
        return new AccountDO(account.getAccountId().getValue(), account.getAccountNumber().getValue(),
                account.getCurrency().getValue(), account.getAvailable().getValue(), account.getDailyLimit().getValue());
    }
}
