package com.inventoryManagement.inventoryManagementSystem.service.TrackingInfoService;

import com.inventoryManagement.inventoryManagementSystem.data.models.TrackingInfo;
import com.inventoryManagement.inventoryManagementSystem.service.exceptions.InventoryException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface TrackingInfoService {
    TrackingInfo findProductInfoByName(String productName) throws InventoryException;
    TrackingInfo save(TrackingInfo trackInfo);
    List<TrackingInfo> findAllTrackingInfo() throws InventoryException;
    void deleteProduct(String productName) throws InventoryException;
    void writeToCsv() throws IOException;
}
