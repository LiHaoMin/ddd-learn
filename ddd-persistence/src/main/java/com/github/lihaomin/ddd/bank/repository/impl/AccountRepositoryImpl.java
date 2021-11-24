package com.github.lihaomin.ddd.bank.repository.impl;

import com.github.lihaomin.ddd.bank.persistence.converter.AccountConverter;
import com.github.lihaomin.ddd.bank.persistence.dao.AccountDAO;
import com.github.lihaomin.ddd.bank.persistence.model.AccountDO;
import com.github.lihaomin.ddd.bank.types.AccountId;
import com.github.lihaomin.ddd.bank.types.AccountNumber;
import com.github.lihaomin.ddd.bank.types.UserId;
import com.github.lihaomin.ddd.domain.entity.Account;
import com.github.lihaomin.ddd.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author lihaomin
 * @date 2021/11/23
 */
@Component
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {
    private final AccountDAO accountDAO;
    private final AccountConverter accountConverter;

    @Override
    public Account find(AccountId id) {
        AccountDO accountDO = accountDAO.findById(id.getValue()).get();
        return accountConverter.toAccount(accountDO);
    }

    @Override
    public Account find(AccountNumber accountNumber) {
        AccountDO accountDO = accountDAO.findByAccountNumber(accountNumber.getValue());
        return accountConverter.toAccount(accountDO);
    }

    @Override
    public Account find(UserId userId) {
        AccountDO accountDO = accountDAO.findById(userId.getValue()).get();
        return accountConverter.toAccount(accountDO);
    }

    @Override
    public Account save(Account account) {
        AccountDO accountDO = accountConverter.fromAccount(account);
        return accountConverter.toAccount(accountDAO.save(accountDO));
    }
}
