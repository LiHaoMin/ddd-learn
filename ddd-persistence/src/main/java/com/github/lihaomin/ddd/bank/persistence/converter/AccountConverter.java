package com.github.lihaomin.ddd.bank.persistence.converter;

import com.github.lihaomin.ddd.bank.persistence.model.AccountDO;
import com.github.lihaomin.ddd.bank.types.Currency;
import com.github.lihaomin.ddd.bank.types.Money;
import com.github.lihaomin.ddd.domain.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author lihaomin
 * @date 2021/11/24
 */
@Mapper(componentModel = "spring", imports = { Money.class, Currency.class })
public interface AccountConverter {

    @Mapping(target = "accountId.value", source = "id")
    @Mapping(target = "accountNumber.value", source = "accountNumber")
    @Mapping(target = "userId.value", source = "id")
    @Mapping(target = "available", expression = "java(new Money(accountDO.getAvailable(), new Currency(accountDO.getCurrency())))")
    @Mapping(target = "dailyLimit.value", source = "dailyLimit")
    Account toAccount(AccountDO accountDO);

    @Mapping(target = "id", source = "accountId.value")
    @Mapping(target = "accountNumber", source = "accountNumber.value")
    @Mapping(target = "available", source = "available.value")
    @Mapping(target = "dailyLimit", source = "dailyLimit.value")
    @Mapping(target = "currency", source = "available.currency.value")
    AccountDO fromAccount(Account account);
}
