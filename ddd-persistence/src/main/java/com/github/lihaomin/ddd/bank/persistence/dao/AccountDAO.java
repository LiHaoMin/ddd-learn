package com.github.lihaomin.ddd.bank.persistence.dao;

import com.github.lihaomin.ddd.bank.persistence.model.AccountDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lihaomin
 * @date 2021/11/23
 */
@Repository
public interface AccountDAO extends JpaRepository<AccountDO, Long> {
    AccountDO findByAccountNumber(String accountNumber);
}
