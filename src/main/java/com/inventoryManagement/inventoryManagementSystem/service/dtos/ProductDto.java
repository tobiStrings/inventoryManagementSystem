package com.inventoryManagement.inventoryManagementSystem.service.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDto {
    @NotNull @NotBlank @NotEmpty
    private String productName;
    private List<String> imageUrls;
    private double priceForEach;
    private  int quantity;
    private String operation;
    private String description;

    public ProductDto(){
        imageUrls = new ArrayList<>();
    }
}
