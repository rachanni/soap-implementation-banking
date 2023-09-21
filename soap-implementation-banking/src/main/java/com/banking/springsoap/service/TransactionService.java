package com.banking.springsoap.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.springsoap.entity.Account;
import com.banking.springsoap.entity.Transaction;
import com.banking.springsoap.gen.TransactionRequest;
import com.banking.springsoap.gen.TransactionResponse;
import com.banking.springsoap.repository.AccountRepository;
import com.banking.springsoap.repository.TransactionRepository;
import java.util.Date;

@Service
public class TransactionService {

	@Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;
    
    public TransactionResponse doTransaction(TransactionRequest transactionRequest) {
    	
    	TransactionResponse transactionResponse=new TransactionResponse();

        Account account = accountRepository.findById(transactionRequest.getId()).get();
        double balance = account.getBalance();
        
        Transaction transaction = new Transaction();
        
        if (transactionRequest.getTransactionType().equals("deposit")) {
            balance = balance + transactionRequest.getAmount();
            account.setBalance(balance);
            transaction.setAccount(account);
           // transaction.setTransactionType(transactionRequest.getTransactionType());
            transaction.setDate(new Date());
            transaction.setAmount(transactionRequest.getAmount());
//            transaction.setAccount(account);
            accountRepository.save(account);
            transactionRepository.save(transaction);

            transactionResponse.setStatus("Transaction Successful ! Amount credited is"+transactionRequest.getAmount()
            +"Updated balance is "+balance);
            transactionResponse.setBalance(balance);
            return transactionResponse;
            
        } else if (transactionRequest.getTransactionType().equals("withdraw")) {
            if (balance < transactionRequest.getAmount()) {
                transactionResponse.setStatus("Insufficient fund !");
                return transactionResponse;
            } else {
                balance = balance- transactionRequest.getAmount();
                account.setBalance(balance);
                transaction.setAccount(account);
                transaction.setAmount(transactionRequest.getAmount());
               // transaction.setTransactionType(transactionRequest.getTransactionType());
                transaction.setDate(new Date());
                transactionRepository.save(transaction);
                accountRepository.save(account);
                transactionResponse.setStatus("Transaction Successful ! Amount debited is "+transactionRequest
                		.getAmount());
                transactionResponse.setBalance(balance);
                
                return transactionResponse;
            }
        } else {
            transactionResponse.setStatus("Invalid Request !");
            return transactionResponse;
        }
    }
}
    	
    	
    


