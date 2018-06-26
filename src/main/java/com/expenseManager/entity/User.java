package com.expenseManager.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class User {

    @Id
    @GeneratedValue
    private Integer id;

    private String nickName;
/*
    @ManyToOne
    private Expense expense;*/
}
