package com.example.lld.payment.services;

import com.example.lld.payment.user.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final List<User> userList;

    public UserService() {
        this.userList = new ArrayList<>();
    }

    public User newUser(String name){
        User user = new User(name);
        userList.add(user);
        return user;
    }

    protected User getUserById(String id){
        User user = userList.stream().filter(u -> u.getUserId().equals(id)).findFirst().orElseThrow();
        return user;
    }
}
