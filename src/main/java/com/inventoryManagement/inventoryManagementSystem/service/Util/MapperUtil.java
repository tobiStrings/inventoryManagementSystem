package com.inventoryManagement.inventoryManagementSystem.service.Util;

import com.inventoryManagement.inventoryManagementSystem.data.models.Product;
import com.inventoryManagement.inventoryManagementSystem.service.dtos.ProductDto;

public class MapperUtil {
    public static Product mapDtoToProduct(ProductDto productToSave, Product product){
//        product.setProductName(pro);
        return new Product();
    }
}
