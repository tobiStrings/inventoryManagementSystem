package com.inventoryManagement.inventoryManagementSystem.controller;

import com.inventoryManagement.inventoryManagementSystem.data.models.Product;
import com.inventoryManagement.inventoryManagementSystem.service.ProductService.ProductService;
import com.inventoryManagement.inventoryManagementSystem.service.ProductService.ProductServiceImpl;
import com.inventoryManagement.inventoryManagementSystem.service.TrackingInfoService.TrackingInfoService;
import com.inventoryManagement.inventoryManagementSystem.service.dtos.ProductDto;
import com.inventoryManagement.inventoryManagementSystem.service.exceptions.InventoryException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {
    private  final ProductService productServiceImpl;
    private final TrackingInfoService trackingInfoServiceImpl;

    @PostMapping("/save")
    public ResponseEntity<?> saveProduct(@RequestBody ProductDto productDto){
        try {
            Product product = productServiceImpl.addProduct(productDto);
            return ResponseEntity.ok().body(product);
        }catch(InventoryException e){
            log.info(e.getLocalizedMessage());
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }

    }

    @PatchMapping("/remove")
    public ResponseEntity<?> removeProduct(@RequestBody ProductDto productDto){
        try {
            productServiceImpl.removeProduct(productDto);
            return ResponseEntity.ok().body("Product(s) removed successfully ");
        } catch (InventoryException e) {
            log.info(e.getLocalizedMessage());
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
    }

    @GetMapping("/findAllProducts")
    public ResponseEntity<?> findAllProducts(){
        try {
            return ResponseEntity.ok().body(productServiceImpl.findAllProducts());
        } catch (InventoryException e) {
            log.info(e.getLocalizedMessage());
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
    }

    @GetMapping("/findAllInfo")
    public ResponseEntity<?> findAllTrackingInfo(){
        try {
            return ResponseEntity.ok().body(trackingInfoServiceImpl.findAllTrackingInfo());
        } catch (InventoryException e) {
            log.info(e.getLocalizedMessage());
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
    }

    @DeleteMapping("/deleteProduct")
    public ResponseEntity<?> deleteProduct(@RequestBody String productName){
        try {
            trackingInfoServiceImpl.deleteProduct(productName);
            return ResponseEntity.ok().body("Product deleted!");
        } catch (InventoryException e) {
            log.info(e.getLocalizedMessage());
            return  ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
    }

    @PutMapping("/writeToCsv")
    public ResponseEntity<?> writeToCsv(){
        try {
            trackingInfoServiceImpl.writeToCsv();
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Successful");
        } catch (IOException e) {
            log.info(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getLocalizedMessage());
        }
    }
}
