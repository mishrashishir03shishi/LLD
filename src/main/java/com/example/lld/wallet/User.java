package com.example.lld.wallet;

public class User {

    private final String userName;

    private final String name;

    private final Integer age;

    private final String gender;

    private final Wallet wallet;

    public User(String userName, String name, Integer age, String gender) {
        this.userName = userName;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.wallet = new Wallet(userName);
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Wallet getWallet() {
        return wallet;
    }
}
