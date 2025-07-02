package com.sgcib.katasgdemo.service;

import com.sgcib.katasgdemo.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisplayTransaction {

    public String print(List<Transaction> transactions) {
        StringBuilder sb = new StringBuilder();
        sb.append("DATE       | AMOUNT | BALANCE\n");

        for (Transaction tx : transactions) {
            sb.append(String.format("%s | %6d | %7d\n",
                    tx.getDate(),
                    tx.getAmount(),
                    tx.getBalanceAfter()));
        }

        return sb.toString();
    }

}
