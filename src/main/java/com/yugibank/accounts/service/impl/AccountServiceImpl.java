package com.yugibank.accounts.service.impl;

import com.yugibank.accounts.constants.AccountConstants;
import com.yugibank.accounts.dto.AccountsDTO;
import com.yugibank.accounts.dto.CustomerDTO;
import com.yugibank.accounts.entity.Accounts;
import com.yugibank.accounts.entity.Customer;
import com.yugibank.accounts.exception.CustomerAlreadyExistsException;
import com.yugibank.accounts.exception.ResourceNotFoundException;
import com.yugibank.accounts.mapper.AccountsMapper;
import com.yugibank.accounts.mapper.CustomerMapper;
import com.yugibank.accounts.repository.AccountRepository;
import com.yugibank.accounts.repository.CustomerRepository;
import com.yugibank.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountsService {

    private AccountRepository accountRepository;

    private CustomerRepository customerRepository;

    /**
     * This method creates a customer
     *
     * @param customerDTO -- customer DTO object
     */
    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.mapToCustomer(customerDTO, new Customer());
        Optional<Customer> customerOptional = customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
        if (customerOptional.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with mobile number: " + customerDTO.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        customerRepository.save(customer);
        accountRepository.save(createNewAccount(customer));
    }

    @Override
    public CustomerDTO fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));

        CustomerDTO customerDTO = CustomerMapper.mapToCustomerDTO(customer, new CustomerDTO());
        customerDTO.setAccountsDTO(new AccountsDTO());
        AccountsMapper.mapToAccountsDTO(accounts, customerDTO.getAccountsDTO());
        return customerDTO;
    }

    @Override
    public boolean updateAccount(CustomerDTO customerDTO) {

        boolean isUpdated = false;
        AccountsDTO accountsDto = customerDTO.getAccountsDTO();
        if (accountsDto != null) {
            Accounts accounts = accountRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDTO, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }


    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymous");
        return newAccount;
    }

}
