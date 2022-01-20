package com.inventoryManagement.inventoryManagementSystem.service.TrackingInfoService;

import com.inventoryManagement.inventoryManagementSystem.data.repositories.TrackingInfoRepository;
import com.inventoryManagement.inventoryManagementSystem.service.exceptions.InventoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DeleteProductTest {
    @Autowired
    private TrackingInfoRepository trackInfoRepoImpl;
    @Autowired
     private TrackingInfoService trackingInfoService;

     @BeforeEach
    void setUp(){

     }

     @Test
    void deleteProductTest() throws InventoryException {
         trackingInfoService.deleteProduct("pepsi");
         assertThat(trackInfoRepoImpl.findByProductName("pepsi")).isNull();
     }
}
