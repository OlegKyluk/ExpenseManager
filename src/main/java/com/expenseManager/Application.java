package com.expenseManager;

import com.expenseManager.entity.Expense;
import com.expenseManager.entity.Product;
import com.expenseManager.service.ExpenseService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    public Application(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private final ExpenseService expenseService;


    @Override
    public void run(String... args) throws IOException, JSONException {
        System.out.println("Command Options: ");
        System.out.println("a: Add");
        System.out.println("b: List");
        System.out.println("c: Clear");
        System.out.println("d: Total");
        System.out.println("?: Display");
        System.out.println("q: Quit");
        Scanner sc = new Scanner(System.in);
        String choice;
        do {
            choice = sc.nextLine();
            switch (choice) {
                case "a":
                    System.out.println("EXAMPLE: 2018-06-27 Milk 13.00 UAH");
                    expenseService.add(new Expense(LocalDate.of(sc.nextInt(), sc.nextInt(), sc.nextInt())),
                            new Product(sc.next(), sc.nextBigDecimal(), sc.next()));
                    System.out.println(expenseService.list());
                    break;
                case "b":
                    System.out.println(expenseService.list());
                    break;
                case "c":
                    System.out.println("EXAMPLE: 2018-06-27");
                    expenseService.clear(LocalDate.of(sc.nextInt(), sc.nextInt(), sc.nextInt()));
                    System.out.println(expenseService.list());
                    break;
                case "d":
                    System.out.println("EXAMPLE: USD");
                    expenseService.total(sc.next());
                    break;
                case "?":
                    System.out.println("Command Options: ");
                    System.out.println("a: Add");
                    System.out.println("b: List");
                    System.out.println("c: Clear");
                    System.out.println("d: Total");
                    System.out.println("?: Display");
                    System.out.println("q: Quit");
                    break;
            }
        } while (!choice.equals("q"));
    }


}




