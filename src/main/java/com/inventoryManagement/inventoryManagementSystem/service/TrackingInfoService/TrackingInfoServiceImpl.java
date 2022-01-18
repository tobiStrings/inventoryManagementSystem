package com.inventoryManagement.inventoryManagementSystem.service.TrackingInfoService;

import com.inventoryManagement.inventoryManagementSystem.data.models.TrackingInfo;
import com.inventoryManagement.inventoryManagementSystem.data.repositories.TrackingInfoRepo;
import com.inventoryManagement.inventoryManagementSystem.service.exceptions.InventoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class TrackingInfoServiceImpl implements TrackingInfoService{
    @Autowired
    private  TrackingInfoRepo trackRepoImpl;
    @Override
    public TrackingInfo findProductInfoByName(String productName) throws InventoryException {
        TrackingInfo productInfo = trackRepoImpl.findByProductName(productName.toLowerCase());
        if (productInfo != null){
            return productInfo;
        }
        throw new InventoryException("Product "+ productName+" does not have information");
    }

    @Override
    public TrackingInfo save(TrackingInfo trackInfo) {
        trackInfo.setProductName(trackInfo.getProductName().toLowerCase());
        return trackRepoImpl.save(trackInfo);
    }

    @Override
    public List<TrackingInfo> findAllTrackingInfo() throws InventoryException {
        if (!trackRepoImpl.findAll().isEmpty()) return trackRepoImpl.findAll();
        throw new InventoryException("There is no tracks being kept");
    }


}
