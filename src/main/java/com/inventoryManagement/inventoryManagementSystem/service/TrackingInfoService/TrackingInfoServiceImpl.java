package com.inventoryManagement.inventoryManagementSystem.service.TrackingInfoService;

import com.inventoryManagement.inventoryManagementSystem.data.models.TrackingInfo;
import com.inventoryManagement.inventoryManagementSystem.data.repositories.TrackingInfoRepository;
import com.inventoryManagement.inventoryManagementSystem.service.ProductService.ProductService;
import com.inventoryManagement.inventoryManagementSystem.service.ProductService.ProductServiceImpl;
import com.inventoryManagement.inventoryManagementSystem.service.dtos.ProductDto;
import com.inventoryManagement.inventoryManagementSystem.service.exceptions.InventoryException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrackingInfoServiceImpl implements TrackingInfoService{

    private final TrackingInfoRepository trackingInfoRepositoryImpl;

    private final ModelMapper mapper = new ModelMapper();
    private final ProductService productServiceImpl;
    @Override
    public TrackingInfo findProductInfoByName(String productName) throws InventoryException {
        TrackingInfo productInfo = trackingInfoRepositoryImpl.findByProductName(productName.toLowerCase());
        if (productInfo != null){
            return productInfo;
        }
        throw new InventoryException("Product "+ productName+" does not have information");
    }

    @Override
    public TrackingInfo save(TrackingInfo trackInfo) {
        trackInfo.setProductName(trackInfo.getProductName().toLowerCase());
        return trackingInfoRepositoryImpl.save(trackInfo);
    }

    @Override
    public List<TrackingInfo> findAllTrackingInfo() throws InventoryException {
        if (!trackingInfoRepositoryImpl.findAll().isEmpty()) return trackingInfoRepositoryImpl.findAll();
        throw new InventoryException("There is no tracks being kept");
    }

    @Override
    public void deleteProduct(String productName) throws InventoryException {
        TrackingInfo productInfoToDelete = findProductInfoByName(productName);
        if (productInfoToDelete == null) throw new InventoryException("Product not found");
        ProductDto productDto = new ProductDto();
        mapper.map(productInfoToDelete,productDto);
        productServiceImpl.removeProduct(productDto);
        trackingInfoRepositoryImpl.deleteByProductName(productName);
    }

    @Override
    public void writeToCsv() throws IOException {
        File csvOutputFile = new File("inventory.csv");
        if(!csvOutputFile.exists()) {
            boolean newFile = csvOutputFile.createNewFile();
        }

        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            trackingInfoRepositoryImpl.findAll().stream()
                    .map(TrackingInfo::toCsvRow)
                    .forEach(pw::println);
        }
    }


}
