package com.expenseManager.repository;

import com.expenseManager.entity.Expense;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Integer> {

    void deleteByDateOfPurchaseIs(LocalDate date);

    Expense findByDateOfPurchaseIs(LocalDate date);

}
