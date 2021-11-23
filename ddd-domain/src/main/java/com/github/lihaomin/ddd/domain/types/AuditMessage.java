package com.github.lihaomin.ddd.domain.types;

import com.github.lihaomin.ddd.bank.types.Money;
import com.github.lihaomin.ddd.domain.entity.Account;
import lombok.Value;

/**
 * @author lihaomin
 * @date 2021/11/23
 */
@Value
public class AuditMessage {
    Account sourceAccount;
    Account targetAccount;
    Money targetMoney;

    public String serialize() {
        return null;
    }

    public static AuditMessage deserialize(String value) {
        return null;
    }
}
