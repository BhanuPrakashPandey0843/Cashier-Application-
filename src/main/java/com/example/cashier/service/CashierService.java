

package com.example.cashier.service;

import com.example.cashier.model.Product;
import com.example.cashier.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CashierService {

    @Autowired
    private ProductRepository productRepository;

    private List<Product> purchaseList = new ArrayList<>();
    private List<String> outOfStockList = new ArrayList<>();

    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    public String purchaseItem(String name, int quantity) {
        Product product = productRepository.findByName(name);
        if (product == null || product.getQuantity() == 0) {
            return "Item not available";
        }
        if (product.getQuantity() < quantity) {
            return "Insufficient stock";
        }
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
        purchaseList.add(product);
        if (product.getQuantity() == 0 && !outOfStockList.contains(name)) {
            outOfStockList.add(name);
        }
        return "Item purchased";
    }

    public List<String> getOutOfStockList() {
        return outOfStockList;
    }

    public String deletePurchaseItem(String name) {
        Product product = productRepository.findByName(name);
        if (product != null) {
            purchaseList.remove(product);
            product.setQuantity(product.getQuantity() + 1);
            productRepository.save(product);
            outOfStockList.remove(name);
            return "Item removed from purchase list";
        }
        return "Item not found";
    }

    public String endPurchase() {
        purchaseList.clear();
        return "Transaction completed";
    }
}
