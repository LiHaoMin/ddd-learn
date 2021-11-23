package com.github.lihaomin.ddd.bank.repository.impl;

import com.github.lihaomin.ddd.bank.persistence.AccountDAO;
import com.github.lihaomin.ddd.bank.persistence.model.AccountDO;
import com.github.lihaomin.ddd.bank.types.AccountId;
import com.github.lihaomin.ddd.bank.types.AccountNumber;
import com.github.lihaomin.ddd.bank.types.UserId;
import com.github.lihaomin.ddd.domain.entity.Account;
import com.github.lihaomin.ddd.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author lihaomin
 * @date 2021/11/23
 */
@Component
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {
    private final AccountDAO accountDAO;

    @Override
    public Account find(AccountId id) {
        return null;
    }

    @Override
    public Account find(AccountNumber accountNumber) {
        return null;
    }

    @Override
    public Account find(UserId userId) {
        AccountDO accountDO = accountDAO.getOne(userId.getValue());
        Account account = new Account();
        // DO 转换实体
        return account;
    }

    @Override
    public Account save(Account account) {
        return null;
    }
}
