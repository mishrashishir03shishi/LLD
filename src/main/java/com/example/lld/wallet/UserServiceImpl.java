package com.example.lld.wallet;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService{

    private final HashMap<String, User> userStore;

    public UserServiceImpl() {
        this.userStore = new HashMap<>();
    }

    @Override
    public void registerUser(String userName, String name, Integer age, String gender) {
        User user = new User(userName, name, age, gender);
        userStore.put(userName,user);
    }

    @Override
    public boolean addMoney(String userId, PaymentMethod method, Integer amount) {
        User user = getUserById(userId);
        if(Objects.isNull(user)){
            throw  new RuntimeException("user not found");
        }

        method.pay(amount);
        user.getWallet().addMoney(amount);
        return false;
    }

    @Override
    public boolean sendMoney(String senderId, String receiverId, Integer amount) {
        return false;
    }

    @Override
    public Integer fetchBalance(String userId) {
        return null;
    }

    @Override
    public List<Transaction> getTransactionHistory(String userId, List<SortStrategy> sortStrategies, List<FilterStrategy> filterStrategies) {
        return null;
    }

    protected User getUserById(String userId){
        return userStore.get(userId);
    }
}
