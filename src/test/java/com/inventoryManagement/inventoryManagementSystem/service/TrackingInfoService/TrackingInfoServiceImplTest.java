package com.inventoryManagement.inventoryManagementSystem.service.TrackingInfoService;

import com.inventoryManagement.inventoryManagementSystem.data.models.TrackingInfo;
import com.inventoryManagement.inventoryManagementSystem.data.repositories.TrackingInfoRepo;
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
import static org.mockito.Mockito.when;

class TrackingInfoServiceImplTest {
    @Mock
    private TrackingInfoRepo trackRepoImpl;
    @InjectMocks
    private TrackingInfoService trackingInfoServiceImpl;

    @BeforeEach
    void setUp(){
        trackingInfoServiceImpl= new TrackingInfoServiceImpl();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test that products info can be found by name")
    void findProductsInfoByName() throws InventoryException {
        TrackingInfo trackingInfo =  new TrackingInfo();
        trackingInfo.setProductName("pepsi");
        trackingInfo.setTotalQuantity(100L);

        when(trackRepoImpl.findByProductName("pepsi")).thenReturn(trackingInfo);

        TrackingInfo productInfo = trackingInfoServiceImpl.findProductInfoByName("Pepsi");
        assertThat(productInfo).isNotNull();
    }

    @Test
    @DisplayName("Test that product info cannot be returned if the product does not have informations")
    void findProductsInfoByNameWhenItHasNoInfo(){
        TrackingInfo trackingInfo =  new TrackingInfo();
        trackingInfo.setProductName("pepsi");
        trackingInfo.setTotalQuantity(100L);

        when(trackRepoImpl.findByProductName("Pepsi")).thenReturn(null);

        assertThrows(InventoryException.class,()-> trackingInfoServiceImpl.findProductInfoByName("Pepsi"));

    }

    @Test
    @DisplayName("Test that trackingInfo can be saved")
    void saveTrackingInfo(){
        TrackingInfo trackingInfo =  new TrackingInfo();
        trackingInfo.setTrackingId("rrtwqtwt4233");
        trackingInfo.setProductName("pepsi");
        trackingInfo.setTotalQuantity(100L);

        when(trackRepoImpl.save(trackingInfo)).thenReturn(trackingInfo);
        TrackingInfo retrievedTrackingInfo = trackingInfoServiceImpl.save(trackingInfo);
        assertThat(retrievedTrackingInfo).isNotNull();
        assertThat(retrievedTrackingInfo.getTrackingId()).isNotNull();
    }

    @Test
    @DisplayName("Find all tracking info when the database is not empty")
    void findAllTrackingInfo() throws InventoryException {
        List<TrackingInfo> trackingInfos =  new ArrayList<>();
        trackingInfos.add(new TrackingInfo());

        when(trackRepoImpl.findAll()).thenReturn(trackingInfos);
        List<TrackingInfo>returnedInfos = trackingInfoServiceImpl.findAllTrackingInfo();
        assertThat(returnedInfos).isNotNull();
        assertThat(returnedInfos).isNotEmpty();
    }

    @Test
    @DisplayName("Find all tracking infos when the database is empty")
    void findAllTrackingInfosInAnEmptyDatabase(){
        List<TrackingInfo> trackingInfos =  new ArrayList<>();

        when(trackRepoImpl.findAll()).thenReturn(trackingInfos);
        assertThrows(InventoryException.class,()->trackingInfoServiceImpl.findAllTrackingInfo());
    }
}