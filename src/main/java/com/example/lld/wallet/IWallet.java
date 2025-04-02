package com.example.lld.wallet;

import java.util.List;

public interface IWallet {

    void spendMoney(int amount);

    void addMoney(int amount);

    List<Transaction> getTransactionHistory();
}
