package com.fizikovnet.hw.dto;

public class Product {

    private Long id;
    private Integer accountNumber;
    private Double balance;
    private String type;

    private User user;

    public Product(Long id, Integer accountNumber, Double balance, String type, User user) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.type = type;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public String getType() {
        return type;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", type=" + type +
                ", user=" + user +
                '}';
    }


}
