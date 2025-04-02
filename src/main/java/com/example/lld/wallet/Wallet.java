package com.example.lld.wallet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class Wallet implements IWallet{

    private final String userId;

    private final List<Transaction> transactionList;

    private int balance;

    public Wallet(String userId) {
        this.userId = userId;
        this.transactionList = new ArrayList<>();
        this.balance = 0;
    }


    @Override
    public void spendMoney(int amount) {
        if(balance-amount<0){
            throw new RuntimeException("Insufficient balance");
        }
        Transaction transaction = new Transaction(UUID.randomUUID().toString(), amount, TransactionType.DEBIT, Calendar.getInstance().getTime());
        transactionList.add(transaction);
        this.balance -= amount;
    }

    @Override
    public void addMoney(int amount) {
        Transaction transaction = new Transaction(UUID.randomUUID().toString(), amount, TransactionType.CREDIT, Calendar.getInstance().getTime());
        transactionList.add(transaction);
        this.balance += amount;
    }

    @Override
    public List<Transaction> getTransactionHistory() {
        List<Transaction> transactions = new ArrayList<>();
        for(Transaction transaction : transactionList){
            Transaction newTransaction = new Transaction(transaction.getTransactionId(), transaction.getAmount(), transaction.getTransactionType(), transaction.getTransactionDate());
            transactions.add(newTransaction);
        }

        return transactions;
    }
}
