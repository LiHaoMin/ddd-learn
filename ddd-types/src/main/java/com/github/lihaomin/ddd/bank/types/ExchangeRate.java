package com.github.lihaomin.ddd.bank.types;

import lombok.Value;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author lihaomin
 * @date 2021/11/23
 */
@Value
public class ExchangeRate {
    BigDecimal foreignExchange;
    Currency source;
    Currency target;

    public Money exchangeTo(Money targetMoney) {
        if (!source.equals(target)) {
            BigDecimal sourceAmount = targetMoney.getValue().divide(foreignExchange, RoundingMode.DOWN);
            return new Money(sourceAmount, target);
        }
        return targetMoney;
    }
}
