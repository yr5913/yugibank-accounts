package com.yugibank.accounts.mapper;

import com.yugibank.accounts.dto.AccountsDTO;
import com.yugibank.accounts.entity.Accounts;
import org.springframework.beans.BeanUtils;

public class AccountsMapper {

    public static AccountsDTO mapToAccountsDTO(Accounts accounts, AccountsDTO accountsDTO) {
        BeanUtils.copyProperties(accounts, accountsDTO);
        return accountsDTO;
    }

    public static Accounts mapToAccounts(AccountsDTO accountsDTO, Accounts accounts) {
        BeanUtils.copyProperties(accountsDTO, accounts);
        return accounts;
    }

}
