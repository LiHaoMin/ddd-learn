package com.github.lihaomin.ddd.web;

import com.github.lihaomin.ddd.bank.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

/**
 * @author lihaomin
 * @date 2021/11/23
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/account")
public class TransferController {
    private final TransferService transferService;

    @PostMapping
    public ResponseEntity<Boolean> transfer(String targetAccountNumber, BigDecimal amount, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        return transferService.transfer(userId, targetAccountNumber, amount, "CNY");
    }
}
