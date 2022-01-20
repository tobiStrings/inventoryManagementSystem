package com.inventoryManagement.inventoryManagementSystem.service.TrackingInfoService;

import com.inventoryManagement.inventoryManagementSystem.data.models.Product;
import com.inventoryManagement.inventoryManagementSystem.data.models.TrackingInfo;
import com.inventoryManagement.inventoryManagementSystem.data.repositories.ProductRepository;
import com.inventoryManagement.inventoryManagementSystem.data.repositories.TrackingInfoRepository;
import com.inventoryManagement.inventoryManagementSystem.service.ProductService.ProductService;
import com.inventoryManagement.inventoryManagementSystem.service.ProductService.ProductServiceImpl;
import com.inventoryManagement.inventoryManagementSystem.service.dtos.ProductDto;
import com.inventoryManagement.inventoryManagementSystem.service.exceptions.InventoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TrackingInfoServiceImplTest {
    @Mock
    private TrackingInfoRepository trackingInfoRepoImpl;
    @Mock
    private ProductRepository productRepositoryImpl;

    @InjectMocks
    private TrackingInfoServiceImpl trackingInfoServiceImpl;

    @Mock
    private ProductService productServiceImpl;

    @BeforeEach
    void setUp(){

        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test that products info can be found by name")
    void findProductsInfoByName() throws InventoryException {
        TrackingInfo trackingInfo =  new TrackingInfo();
        trackingInfo.setProductName("pepsi");
        trackingInfo.setTotalQuantity(100L);

        when(trackingInfoRepoImpl.findByProductName("pepsi")).thenReturn(trackingInfo);

        TrackingInfo productInfo = trackingInfoServiceImpl.findProductInfoByName("Pepsi");
        assertThat(productInfo).isNotNull();
    }

    @Test
    @DisplayName("Test that product info cannot be returned if the product does not have informations")
    void findProductsInfoByNameWhenItHasNoInfo(){
        TrackingInfo trackingInfo =  new TrackingInfo();
        trackingInfo.setProductName("pepsi");
        trackingInfo.setTotalQuantity(100L);

        when(trackingInfoRepoImpl.findByProductName("Pepsi")).thenReturn(null);

        assertThrows(InventoryException.class,()-> trackingInfoServiceImpl.findProductInfoByName("Pepsi"));

    }

    @Test
    @DisplayName("Test that trackingInfo can be saved")
    void saveTrackingInfo(){
        TrackingInfo trackingInfo =  new TrackingInfo();
        trackingInfo.setTrackingId("rrtwqtwt4233");
        trackingInfo.setProductName("pepsi");
        trackingInfo.setTotalQuantity(100L);

        when(trackingInfoRepoImpl.save(trackingInfo)).thenReturn(trackingInfo);
        TrackingInfo retrievedTrackingInfo = trackingInfoServiceImpl.save(trackingInfo);
        assertThat(retrievedTrackingInfo).isNotNull();
        assertThat(retrievedTrackingInfo.getTrackingId()).isNotNull();
    }

    @Test
    @DisplayName("Find all tracking info when the database is not empty")
    void findAllTrackingInfo() throws InventoryException {
        List<TrackingInfo> trackingInfos =  new ArrayList<>();
        trackingInfos.add(new TrackingInfo());

        when(trackingInfoRepoImpl.findAll()).thenReturn(trackingInfos);
        List<TrackingInfo>returnedInfos = trackingInfoServiceImpl.findAllTrackingInfo();
        assertThat(returnedInfos).isNotNull();
        assertThat(returnedInfos).isNotEmpty();
    }

    @Test
    @DisplayName("Find all tracking infos when the database is empty")
    void findAllTrackingInfosInAnEmptyDatabase(){
        List<TrackingInfo> trackingInfos =  new ArrayList<>();

        when(trackingInfoRepoImpl.findAll()).thenReturn(trackingInfos);
        assertThrows(InventoryException.class,()->trackingInfoServiceImpl.findAllTrackingInfo());
    }

    @Test
    @DisplayName("Test that product can be deleted")
    void deleteProduct() throws InventoryException {
        TrackingInfo trackingInfo =  new TrackingInfo();
        trackingInfo.setProductName("pepsi");
        trackingInfo.setTotalQuantity(100L);
        Product product = new Product();

        ProductDto productDto = new ProductDto();
        productDto.setProductName("pepsi");

        when(trackingInfoRepoImpl.findByProductName("pepsi")).thenReturn(trackingInfo);
        when(trackingInfoRepoImpl.save(any())).thenReturn(trackingInfo);
        when(productServiceImpl.removeProduct(productDto)).thenReturn(product);
        trackingInfoServiceImpl.deleteProduct("Pepsi");
    }
}