package com.example.lld.wallet;

import java.util.Date;

public class Transaction {

    private final String transactionId;

    private final Integer amount;

    private final TransactionType transactionType;

    private final Date transactionDate;


    public Transaction(String transactionId, Integer amount, TransactionType transactionType, Date transactionDate) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Integer getAmount() {
        return amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }
}
