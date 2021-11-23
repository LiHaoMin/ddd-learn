package com.github.lihaomin.ddd.bank.service;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author lihaomin
 * @date 2021/11/23
 */
@Component
public class YahooForexService {
    public BigDecimal getExchangeRate(String sourceCurrency, String targetCurrency) {
        return BigDecimal.ONE;
    }
}
