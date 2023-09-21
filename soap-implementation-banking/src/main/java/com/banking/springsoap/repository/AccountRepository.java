package com.banking.springsoap.repository;

import com.banking.springsoap.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
