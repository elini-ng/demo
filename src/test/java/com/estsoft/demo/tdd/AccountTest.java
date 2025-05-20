package com.estsoft.demo.tdd;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * 1. create account
 * 2. balance inquiry
 * 3. deposit & withdrawal
 */
public class AccountTest {
    //ask > answer > refine

    @Test
    public void testCreateAccount() {
        Account account = new Account(100000);
        assertNotNull(account);
    }

    @Test
    public void testGetBalance() throws Exception {
        Account account = new Account(100000);
        assertEquals(100000, account.getBalance());

        account = new Account(50000);
        assertEquals(50000, account.getBalance());

        account = new Account(0);
        assertEquals(0, account.getBalance());
    }

    @Test
    public void testDeposit() throws Exception {
        //1. Create account, 2. Deposit, 3. Balance inquiry
        Account account = new Account(10000);
        account.deposit(50000);
        assertEquals(60000, account.getBalance());
    }

    @Test
    public void testWithdraw() throws Exception {
        Account account = new Account(10000);
        account.withdraw(5000);
        assertEquals(5000, account.getBalance());

        assertThrowsExactly(RuntimeException.class, () -> account.withdraw(100000));
    }
}
