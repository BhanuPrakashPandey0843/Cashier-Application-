
package com.example.cashier.repository;

import com.example.cashier.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
    Product findByName(String name);
}