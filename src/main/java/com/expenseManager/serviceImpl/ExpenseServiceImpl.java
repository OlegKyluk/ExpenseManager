package com.expenseManager.serviceImpl;

import com.expenseManager.entity.Expense;
import com.expenseManager.entity.Product;
import com.expenseManager.fixer.api.service.ConnectorService;
import com.expenseManager.fixer.api.service.CurrencyAndValueParserService;
import com.expenseManager.repository.ExpenseRepository;
import com.expenseManager.service.ExpenseService;
import com.expenseManager.service.ProductService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    private final CurrencyAndValueParserService parserService;

    private final ConnectorService connectorService;

    private final ProductService productService;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository, CurrencyAndValueParserService parserService, ConnectorService connectorService, ProductService productService) {
        this.expenseRepository = expenseRepository;
        this.parserService = parserService;
        this.connectorService = connectorService;
        this.productService = productService;
    }

    @Override
    public void add(Expense expense, Product product) {
        Expense existDate = expenseRepository.findByDateOfPurchaseIs(expense.getDateOfPurchase());
        if (existDate != null) {
            product.setExpense(existDate);
        } else {
            product.setExpense(expense);
            expenseRepository.save(expense);
        }
        productService.save(product);

    }

    @Override
    public Iterable<Expense> list() {
        return expenseRepository.findAll();
    }

    @Override
    @Transactional
    public void clear(LocalDate date) {
        expenseRepository.deleteByDateOfPurchaseIs(date);
    }

    @Override
    public void total(String currency) throws IOException, JSONException {
        BigDecimal requiredCurrency = new BigDecimal(parserService.parse(connectorService.sendRequest()).get(currency)).setScale(2, 1);
        BigDecimal total = costConverter().multiply(requiredCurrency).setScale(2, 1);
        System.out.println(total + " " + currency);
    }

    private BigDecimal costConverter() throws IOException, JSONException {
        BigDecimal total = new BigDecimal(0);
        for (Product p : productService.findAll()) {
            BigDecimal onePurchasePrice = p.getAmountOfMoney();
            BigDecimal currentBuyValueCurrency = new BigDecimal(parserService.parse(connectorService.sendRequest()).get(p.getCurrency()));
            BigDecimal localTotal = new BigDecimal(String.valueOf(onePurchasePrice.divide(currentBuyValueCurrency, 1)));
            total = total.add(localTotal);
        }
        return total;
    }
}