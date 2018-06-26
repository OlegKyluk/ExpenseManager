package com.expenseManager.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private BigDecimal amountOfMoney;

    private String currency;

    @ManyToOne
    private Expense expense;

    public Product() {
    }

    public Product(String name, BigDecimal amountOfMoney, String currency) {
        this.name = name;
        this.amountOfMoney = amountOfMoney;
        this.currency = currency;

    }

    @Override
    public String toString() {
        return name + "  " +
                amountOfMoney + "  " +
                currency + '\n';
    }
}
