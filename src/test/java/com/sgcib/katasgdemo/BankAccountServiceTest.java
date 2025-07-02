package com.sgcib.katasgdemo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import com.sgcib.katasgdemo.model.TransactionType;
import com.sgcib.katasgdemo.repository.TransactionRepository;
import com.sgcib.katasgdemo.service.BankAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.List;

public class BankAccountServiceTest {

    @Mock
    private TransactionRepository repository;

    @InjectMocks
    private BankAccountService bankAccountService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDepositAddsTransactionIncreasesBalance() {
        bankAccountService.deposit(100);

        verify(repository).save(argThat(tx ->
                tx.getAmount() == 100 &&
                        tx.getBalanceAfter() == 100 &&
                        tx.getType() == TransactionType.DEPOSIT
        ));
    }

    @Test
    public void testWithdrawAddsTransactionAndDecreasesBalance() {
        // D'abord faire un dépôt pour avoir du solde
        bankAccountService.deposit(200);
        bankAccountService.withdraw(50);

        verify(repository, times(2)).save(any());
        verify(repository).save(argThat(tx ->
                tx.getAmount() == 50 &&
                        tx.getBalanceAfter() == 150 &&
                        tx.getType() == TransactionType.WITHDRAWAL
        ));
    }

    @Test
    public void testWithdrawThrowsWhenInsufficientFunds() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bankAccountService.withdraw(100);
        });

        assertEquals("Insufficient funds", exception.getMessage());
    }

    @Test
    public void testDepositWithNegativeAmountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> bankAccountService.deposit(-20));
    }
}

