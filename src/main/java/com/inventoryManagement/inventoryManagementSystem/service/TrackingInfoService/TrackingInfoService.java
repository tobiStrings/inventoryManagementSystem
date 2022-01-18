package com.inventoryManagement.inventoryManagementSystem.service.TrackingInfoService;

import com.inventoryManagement.inventoryManagementSystem.data.models.TrackingInfo;
import com.inventoryManagement.inventoryManagementSystem.service.exceptions.InventoryException;

import java.util.List;

public interface TrackingInfoService {
    TrackingInfo findProductInfoByName(String productName) throws InventoryException;
    TrackingInfo save(TrackingInfo trackInfo);
    List<TrackingInfo> findAllTrackingInfo() throws InventoryException;
}
