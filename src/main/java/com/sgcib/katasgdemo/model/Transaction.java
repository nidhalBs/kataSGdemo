package com.sgcib.katasgdemo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private final LocalDate date;
    private final int amount;
    private final int balanceAfter;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

  

    public Transaction(LocalDate date, int amount, int balanceAfter, TransactionType type) {
        this.date = date;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.type = type;
    }

    public Transaction() {

        balanceAfter = 0;
        amount = 0;
        date = null;
    }
}
