package com.inventoryManagement.inventoryManagementSystem.controller;

import com.inventoryManagement.inventoryManagementSystem.data.models.Product;
import com.inventoryManagement.inventoryManagementSystem.service.ProductService.ProductService;
import com.inventoryManagement.inventoryManagementSystem.service.ProductService.ProductServiceImpl;
import com.inventoryManagement.inventoryManagementSystem.service.dtos.ProductDto;
import com.inventoryManagement.inventoryManagementSystem.service.exceptions.InventoryException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private  final ProductService productServiceImpl;

    @PostMapping("product")
    public ResponseEntity<?> saveProduct(@RequestBody ProductDto productDto){
        try {
            Product product = productServiceImpl.addProduct(productDto);
            return ResponseEntity.ok().body(product);
        }catch(InventoryException e){
            log.info(e.getLocalizedMessage());
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }

    }
}
