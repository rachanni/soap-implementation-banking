package com.banking.springsoap.repository;

import com.banking.springsoap.entity.Account;
import com.banking.springsoap.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    List<Transaction> findByAccount(Account account);


}
