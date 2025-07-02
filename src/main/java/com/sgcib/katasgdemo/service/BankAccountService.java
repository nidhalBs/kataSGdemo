package com.sgcib.katasgdemo.service;

import com.sgcib.katasgdemo.model.Transaction;
import com.sgcib.katasgdemo.model.TransactionType;
import com.sgcib.katasgdemo.repository.TransactionRepository;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Getter
public class BankAccountService {

    private int balance = 0;
   // private final List<Transaction> transactions = new ArrayList<>();
    private final TransactionRepository repository;

    public BankAccountService(TransactionRepository repository) {
        this.repository = repository;
    }

    public void deposit(int amount) {
        validateAmount(amount);
        balance += amount;
        repository.save(new Transaction(LocalDate.now(), amount, balance, TransactionType.DEPOSIT));
    }

    public void withdraw(int amount) {
        validateAmount(amount);
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balance -= amount;
        repository.save(new Transaction(LocalDate.now(), amount, balance, TransactionType.WITHDRAWAL));
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> reversed = repository.findAll();
        Collections.reverse(reversed); // Most recent first
        System.out.println(">> Transaction list size: " + reversed.size());
        return reversed;
    }

    private void validateAmount(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
    }
}
