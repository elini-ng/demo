package com.estsoft.demo.tdd;

public class Account {
    private int balance;

    public Account(int money) {
        this.balance = money;
    }

    public int getBalance() {
        return this.balance;
    }

    public void deposit(int money) {
        balance = balance + money;
    }

    public void withdraw(int money) {
        if (balance < money) {
            throw new RuntimeException("Insufficient balance! Current balance: " + balance + ", Requested amount: " + money);
        }

        balance = balance - money;
    }
}
