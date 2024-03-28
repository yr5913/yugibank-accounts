package com.yugibank.accounts.repository;

import com.yugibank.accounts.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Long> {

    Optional<Accounts> findByCustomerId(Long customerId);

}
