package com.github.lihaomin.ddd.bank.persistence.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author lihaomin
 * @date 2021/11/23
 */
@Data
@Entity(name = "T_ACCOUNT")
public class AccountDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String currency;
    private BigDecimal available;
    private BigDecimal dailyLimit;
}
