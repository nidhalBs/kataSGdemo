package com.sgcib.katasgdemo.controller;


import com.sgcib.katasgdemo.model.Transaction;
import com.sgcib.katasgdemo.service.BankAccountService;
import com.sgcib.katasgdemo.service.DisplayTransaction;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/account")
public class ManageAccountController {


    private final BankAccountService accountService;
    private final DisplayTransaction displayTransaction;


    public ManageAccountController(BankAccountService accountService, DisplayTransaction displayTransaction) {
        this.accountService = accountService;
        this.displayTransaction = displayTransaction;
    }

    @PostMapping("/deposit")
    public void deposit(@RequestParam int amount) {
        accountService.deposit(amount);
    }

    @PostMapping("/withdraw")
    public void withdraw(@RequestParam int amount) {
        accountService.withdraw(amount);
    }

    @GetMapping("/transactions")
    public List<Transaction> getTransactions() {
        return accountService.getAllTransactions();
    }

    @GetMapping("/statement")
    public String printStatement() {
        return displayTransaction.print(accountService.getAllTransactions());
    }

    @GetMapping("/balance")
    public int getBalance() {
        return accountService.getBalance();
    }

}
