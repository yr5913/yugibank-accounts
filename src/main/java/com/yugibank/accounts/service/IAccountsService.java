package com.yugibank.accounts.service;

import com.yugibank.accounts.dto.CustomerDTO;

public interface IAccountsService {

    /**
     * This method creates a customer
     * @param customerDTO -- customer DTO object
     */
    void createAccount(CustomerDTO customerDTO);
}
