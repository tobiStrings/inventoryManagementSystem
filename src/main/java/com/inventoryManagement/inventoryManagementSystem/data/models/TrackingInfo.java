package com.inventoryManagement.inventoryManagementSystem.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Document
public class TrackingInfo {
    @Id
    private String trackingId;
    private String productName;
    private Long totalQuantity ;
    private LocalDateTime entryTime;
    private LocalDateTime removedTime;
    private List<Product> products;
    public  TrackingInfo(){
        products = new ArrayList<>();
        totalQuantity = 0L;
    }

    public String toCsvRow() {
        return Stream.of(trackingId, productName, totalQuantity, entryTime)
                .map(value -> String.valueOf(value).replaceAll("\"", "\"\""))
                .map(value -> Stream.of("\"", ",").anyMatch(value::contains) ? "\"" + value + "\"" : value)
                .collect(Collectors.joining(","));

    }
}
