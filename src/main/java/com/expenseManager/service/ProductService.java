package com.expenseManager.service;

import com.expenseManager.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product save(Product product);
}
