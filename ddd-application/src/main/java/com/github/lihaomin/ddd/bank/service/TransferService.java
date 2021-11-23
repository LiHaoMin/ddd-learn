package com.github.lihaomin.ddd.bank.service;

import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

/**
 * @author lihaomin
 * @date 2021/11/23
 */
public interface TransferService {
    ResponseEntity<Boolean> transfer(Long sourceUserId, String targetAccountNumber, BigDecimal targetAmount, String targetCurrency);
}
