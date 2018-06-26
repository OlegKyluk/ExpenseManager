package com.expenseManager.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Expense {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private LocalDate dateOfPurchase;


    @OneToMany(mappedBy = "expense", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Product> productList;

    public Expense() {
    }

    public Expense(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }


    @Override
    public String toString() {
        return "\n" + dateOfPurchase + "\n" +
                productList;
    }
}
