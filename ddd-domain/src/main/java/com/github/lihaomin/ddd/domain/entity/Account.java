package com.github.lihaomin.ddd.domain.entity;

import com.github.lihaomin.ddd.bank.types.AccountId;
import com.github.lihaomin.ddd.bank.types.AccountNumber;
import com.github.lihaomin.ddd.bank.types.Currency;
import com.github.lihaomin.ddd.bank.types.Money;
import com.github.lihaomin.ddd.bank.types.UserId;
import com.github.lihaomin.ddd.exception.DailyLimitExceededException;
import com.github.lihaomin.ddd.exception.InsufficientFundsException;
import com.github.lihaomin.ddd.exception.InvalidCurrencyException;
import lombok.Data;

/**
 * @author lihaomin
 * @date 2021/11/23
 */
@Data
public class Account {
    private AccountId id;
    private AccountNumber accountNumber;
    private UserId userId;
    private Money available;
    private Money dailyLimit;

    public Currency getCurrency() {
        return this.available.getCurrency();
    }

    /**
     * 转入
     */
    public void withdraw(Money money) {
        if (!this.getCurrency().equals(money.getCurrency())) {
            throw new InvalidCurrencyException();
        }
        this.available = this.available.add(money);
    }

    /**
     * 转出
     */
    public void deposit(Money money) {
        if (this.available.compareTo(money) < 0) {
            throw new InsufficientFundsException();
        }
        if (this.dailyLimit.compareTo(money) < 0) {
            throw new DailyLimitExceededException();
        }
        this.available = this.available.subtract(money);
    }
}
