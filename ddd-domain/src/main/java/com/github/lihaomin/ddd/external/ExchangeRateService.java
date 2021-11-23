package com.github.lihaomin.ddd.external;

import com.github.lihaomin.ddd.bank.types.Currency;
import com.github.lihaomin.ddd.bank.types.ExchangeRate;

/**
 * @author lihaomin
 * @date 2021/11/23
 */
public interface ExchangeRateService {
    ExchangeRate getExchangeRate(Currency source, Currency target);
}
