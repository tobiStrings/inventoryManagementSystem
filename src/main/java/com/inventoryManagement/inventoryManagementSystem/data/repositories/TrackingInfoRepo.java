package com.inventoryManagement.inventoryManagementSystem.data.repositories;

import com.inventoryManagement.inventoryManagementSystem.data.models.TrackingInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TrackingInfoRepo extends MongoRepository<TrackingInfo,String> {
    TrackingInfo findByProductName(String productName);
    void deleteByProductName(String productName);

}
