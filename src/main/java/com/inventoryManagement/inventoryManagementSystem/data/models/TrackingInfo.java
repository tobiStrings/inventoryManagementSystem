package com.inventoryManagement.inventoryManagementSystem.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class TrackingInfo {
    @Id
    private String trackingId;
    private String productName;
    private Long totalQuantity ;
    private LocalDateTime entryTime;
    private List<Product> products;
    public  TrackingInfo(){
        products = new ArrayList<>();
        totalQuantity = 0L;
    }
}
