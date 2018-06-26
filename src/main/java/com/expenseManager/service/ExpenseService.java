package com.expenseManager.service;

import com.expenseManager.entity.Expense;
import com.expenseManager.entity.Product;
import org.json.JSONException;

import java.io.IOException;
import java.time.LocalDate;

public interface ExpenseService {
    void add(Expense expense, Product product);

    Iterable<Expense> list();

    void clear(LocalDate date);

    void total(String currency) throws IOException, JSONException;
}
