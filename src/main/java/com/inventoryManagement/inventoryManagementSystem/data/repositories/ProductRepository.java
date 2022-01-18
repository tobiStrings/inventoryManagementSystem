package com.inventoryManagement.inventoryManagementSystem.data.repositories;

import com.inventoryManagement.inventoryManagementSystem.data.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {
    Optional<List<Product>> findByProductName(String productName);
    void deleteByProductName(String productName);

}
