package com.github.lihaomin.ddd.bank.types;

import lombok.Value;

import java.math.BigDecimal;

/**
 * @author lihaomin
 * @date 2021/11/23
 */
@Value
public class Money implements Comparable<Money> {
    BigDecimal value;
    Currency currency;

    public Money add(Money money) {
        return new Money(value.add(money.value), currency);
    }

    public Money subtract(Money money) {
        return new Money(value.subtract(money.value), currency);
    }

    @Override
    public int compareTo(Money o) {
        return value.compareTo(o.getValue());
    }
}
