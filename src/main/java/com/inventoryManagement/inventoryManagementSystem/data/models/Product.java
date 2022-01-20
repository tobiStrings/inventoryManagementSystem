package com.inventoryManagement.inventoryManagementSystem.data.models;

import com.inventoryManagement.inventoryManagementSystem.data.enums.Operation;
import jdk.jfr.Timestamp;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class Product {
    @Id
    private String id;
    private String productName;
    private List<String> imageUrls;
    private LocalDateTime savedTime;
    private LocalDateTime deletedTime;
    private double priceForEach;
    private  Long quantity;
    private TrackingInfo trackInfo;
    private String description;
    private Operation operation;

    public Product(){
        imageUrls = new ArrayList<>();
    }
}
