package com.fizikovnet.hw.dto;

public class Product {

    private Long id;
    private Integer accountNumber;
    private Double balance;
    private String type;

    public Product(Long id, Integer accountNumber, Double balance, String type) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.type = type;
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", type=" + type +
                '}';
    }
}
