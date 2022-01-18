package com.inventoryManagement.inventoryManagementSystem.service.ProductService;

import com.inventoryManagement.inventoryManagementSystem.data.models.Product;
import com.inventoryManagement.inventoryManagementSystem.service.dtos.ProductDto;
import com.inventoryManagement.inventoryManagementSystem.service.exceptions.InventoryException;
import com.inventoryManagement.inventoryManagementSystem.service.exceptions.SaveProductDtoException;

import java.util.List;

public interface ProductService {
    Product addProduct(ProductDto productToSave) throws SaveProductDtoException;
    Product removeProduct(ProductDto productToBeRemoved)throws InventoryException;
    List<Product> findAllProducts() throws InventoryException;
}
