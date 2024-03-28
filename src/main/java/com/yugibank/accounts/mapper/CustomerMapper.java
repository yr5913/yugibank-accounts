package com.yugibank.accounts.mapper;

import com.yugibank.accounts.dto.CustomerDTO;
import com.yugibank.accounts.entity.Customer;
import org.springframework.beans.BeanUtils;

public class CustomerMapper {

    public static CustomerDTO mapToCustomerDTO(Customer customer, CustomerDTO customerDTO) {
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

    public static Customer mapToCustomer(CustomerDTO customerDTO, Customer customer) {
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

}
