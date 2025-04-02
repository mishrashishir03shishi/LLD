package com.example.lld.wallet;

import java.util.List;

public interface UserService {

    public void registerUser(String userName, String name, Integer age, String gender);

    public boolean addMoney(String userId, PaymentMethod method, Integer amount);

    public boolean sendMoney(String senderId, String receiverId, Integer amount);

    public Integer fetchBalance(String userId);

    public List<Transaction> getTransactionHistory(String userId, List<SortStrategy> sortStrategies, List<FilterStrategy> filterStrategies);
}
