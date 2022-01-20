package com.inventoryManagement.inventoryManagementSystem.data.repositories;

import com.inventoryManagement.inventoryManagementSystem.data.models.TrackingInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrackingInfoRepositoryTest {

    @Autowired
    private TrackingInfoRepository trackingInfoRepositoryImpl;

    @Test
    public void saveTrackingInfo(){
        TrackingInfo trackingInfo = new TrackingInfo();
        trackingInfo.setTotalQuantity(1L);
        trackingInfo.setEntryTime(LocalDateTime.now());
        trackingInfo.setProductName("Pepsi");
    }
    @Test
    void findByProductName() {
    }

    @Test
    void deleteByProductName() {
    }


}