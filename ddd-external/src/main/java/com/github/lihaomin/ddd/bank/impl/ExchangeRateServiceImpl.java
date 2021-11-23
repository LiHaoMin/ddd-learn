package com.github.lihaomin.ddd.bank.impl;

import com.github.lihaomin.ddd.bank.service.YahooForexService;
import com.github.lihaomin.ddd.bank.types.Currency;
import com.github.lihaomin.ddd.bank.types.ExchangeRate;
import com.github.lihaomin.ddd.external.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author lihaomin
 * @date 2021/11/23
 */
@Component
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {
    private final YahooForexService yahooForexService;

    @Override
    public ExchangeRate getExchangeRate(Currency source, Currency target) {
        if (source.equals(target)) {
            return new ExchangeRate(BigDecimal.ONE, source, target);
        }
        BigDecimal forex = yahooForexService.getExchangeRate(source.getValue(), target.getValue());
        return new ExchangeRate(forex, source, target);
    }
}
