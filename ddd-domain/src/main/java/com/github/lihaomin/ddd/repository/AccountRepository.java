package com.github.lihaomin.ddd.repository;

import com.github.lihaomin.ddd.bank.types.AccountId;
import com.github.lihaomin.ddd.bank.types.AccountNumber;
import com.github.lihaomin.ddd.bank.types.UserId;
import com.github.lihaomin.ddd.domain.entity.Account;

/**
 * @author lihaomin
 * @date 2021/11/23
 */
public interface AccountRepository {
    Account find(AccountId id);
    Account find(AccountNumber accountNumber);
    Account find(UserId userId);
    Account save(Account account);
}
