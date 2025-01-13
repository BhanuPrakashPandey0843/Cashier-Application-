// src\main\java\com\example\cashier\controller\CashierController.java

package com.example.cashier.controller;

import com.example.cashier.service.CashierService;
import com.example.cashier.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cashier")
public class CashierController {

    @Autowired
    private CashierService cashierService;

    @GetMapping("/products")
    public List<Product> listProducts() {
        return cashierService.listProducts();
    }

    @PostMapping("/purchase")
    public String purchaseItem(@RequestParam String name, @RequestParam int quantity) {
        return cashierService.purchaseItem(name, quantity);
    }

    @GetMapping("/outOfStock")
    public List<String> getOutOfStockList() {
        return cashierService.getOutOfStockList();
    }

    @DeleteMapping("/delete")
    public String deletePurchaseItem(@RequestParam String name) {
        return cashierService.deletePurchaseItem(name);
    }

    @PostMapping("/end")
    public String endPurchase() {
        return cashierService.endPurchase();
    }
}