package com.inventoryManagement.inventoryManagementSystem.service.ProductService;

import com.inventoryManagement.inventoryManagementSystem.data.enums.Operation;
import com.inventoryManagement.inventoryManagementSystem.data.models.Product;
import com.inventoryManagement.inventoryManagementSystem.data.models.TrackingInfo;
import com.inventoryManagement.inventoryManagementSystem.data.repositories.ProductRepository;
import com.inventoryManagement.inventoryManagementSystem.data.repositories.TrackingInfoRepo;
import com.inventoryManagement.inventoryManagementSystem.service.TrackingInfoService.TrackingInfoService;
import com.inventoryManagement.inventoryManagementSystem.service.TrackingInfoService.TrackingInfoServiceImpl;
import com.inventoryManagement.inventoryManagementSystem.service.dtos.ProductDto;
import com.inventoryManagement.inventoryManagementSystem.service.exceptions.InventoryException;
import com.inventoryManagement.inventoryManagementSystem.service.exceptions.SaveProductDtoException;
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

public class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepositoryImpl;
    @Mock
    private TrackingInfoRepo trackRepoImpl;
    @InjectMocks
    private ProductService productServiceImpl;
    @InjectMocks
    private TrackingInfoService trackInfoServiceImpl;

    @BeforeEach
    void setUp(){
        productServiceImpl = new ProductServiceImpl();
        trackInfoServiceImpl = new TrackingInfoServiceImpl();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test that product can be saved successfully")
    void saveProduct() throws SaveProductDtoException {
        ProductDto saveProductDto = new ProductDto();
        saveProductDto.setProductName("Pepsi");
        saveProductDto.setOperation("add");
        saveProductDto.setPriceForEach(150.0);
        saveProductDto.setQuantity(4);
        saveProductDto.getImageUrls().add("ggsttwef643fdc");

        Product productToBeReturned = new Product();
        productToBeReturned.setProductName("Pepsi");
        saveProductDto.setOperation("add");
        productToBeReturned.setPriceForEach(150.0);
        productToBeReturned.setQuantity(4L);
        productToBeReturned.getImageUrls().add("ggsttwef643fdc");

        when(productRepositoryImpl.save(any())).thenReturn(productToBeReturned) ;
        when(trackRepoImpl.findByProductName("Pepsi")).thenReturn(null);
        Product returnedProduct = productServiceImpl.addProduct(saveProductDto);
        assertThat(returnedProduct).isNotNull();
    }

    @Test
    @DisplayName("Test that product cannot be added if product name is null/empty/null")
    void saveProductWhenProductNameIsNull(){
        ProductDto saveProductDto = new ProductDto();
        saveProductDto.setPriceForEach(150.0);
        saveProductDto.setQuantity(4);
        saveProductDto.setOperation("add");
        saveProductDto.getImageUrls().add("ggsttwef643fdc");

        Product productToBeReturned = new Product();
        productToBeReturned.setPriceForEach(150.0);
        productToBeReturned.setQuantity(4L);
        productToBeReturned.setOperation(Operation.ADD);
        productToBeReturned.getImageUrls().add("ggsttwef643fdc");

        when(trackRepoImpl.findByProductName("Pepsi")).thenReturn(null);
        when(productRepositoryImpl.save(any())).thenReturn(productToBeReturned);
        assertThrows(SaveProductDtoException.class,()-> productServiceImpl.addProduct(saveProductDto));
    }

    @Test
    @DisplayName("Test that product cannot be aded if price per each product to be saved is not given")
    void saveProductWhenPricePerEachProductIsNotGiven(){
        ProductDto saveProductDto = new ProductDto();
        saveProductDto.setProductName("Pepsi");
        saveProductDto.setQuantity(4);
        saveProductDto.setOperation("add");
        saveProductDto.getImageUrls().add("ggsttwef643fdc");

        Product productToBeReturned = new Product();
        productToBeReturned.setProductName("Pepsi");
        productToBeReturned.setQuantity(4L);
        productToBeReturned.getImageUrls().add("ggsttwef643fdc");

        when(productRepositoryImpl.save(any())).thenReturn(productToBeReturned);
        when(trackRepoImpl.findByProductName("Pepsi")).thenReturn(null);
        assertThrows(SaveProductDtoException.class,()-> productServiceImpl.addProduct(saveProductDto));
    }

    @Test
    @DisplayName("Test that product cannot be added if product quantity is not added")
    void saveProductWhenProductQuantityIsNotGiven(){
        ProductDto saveProductDto = new ProductDto();
        saveProductDto.setProductName("Pepsi");
        saveProductDto.setPriceForEach(150.0);
        saveProductDto.setOperation("add");
        saveProductDto.getImageUrls().add("ggsttwef643fdc");

        Product productToBeReturned = new Product();
        productToBeReturned.setProductName("Pepsi");
        productToBeReturned.setPriceForEach(150.0);
        productToBeReturned.getImageUrls().add("ggsttwef643fdc");

        when(productRepositoryImpl.save(any())).thenReturn(productToBeReturned);
        when(trackRepoImpl.findByProductName("Pepsi")).thenReturn(null);
        assertThrows(SaveProductDtoException.class,()-> productServiceImpl.addProduct(saveProductDto));
    }

    @Test
    @DisplayName("Test that product cannot be added if at least one image url is not added")
    void saveProductWhenNoImageUrlIsGiven(){
        ProductDto saveProductDto = new ProductDto();
        saveProductDto.setProductName("Pepsi");
        saveProductDto.setPriceForEach(150.0);
        saveProductDto.setOperation("add");
        saveProductDto.setQuantity(4);

        Product productToBeReturned = new Product();
        productToBeReturned.setProductName("Pepsi");
        productToBeReturned.setPriceForEach(150.0);
        productToBeReturned.setQuantity(4L);

        when(productRepositoryImpl.save(any())).thenReturn(productToBeReturned);
        when(trackRepoImpl.findByProductName("Pepsi")).thenReturn(null);
        assertThrows(SaveProductDtoException.class,()-> productServiceImpl.addProduct(saveProductDto));
    }

    @Test
    @DisplayName("Test  that product cannot be added if an operation is not given")
    void saveProductWhenOperationIsNotSpecified(){
        ProductDto saveProductDto = new ProductDto();
        saveProductDto.setProductName("Pepsi");
        saveProductDto.setPriceForEach(150.0);
        saveProductDto.setQuantity(4);
        saveProductDto.getImageUrls().add("ggsttwef643fdc");

        Product productToBeReturned = new Product();
        productToBeReturned.setProductName("Pepsi");
        productToBeReturned.setPriceForEach(150.0);
        productToBeReturned.setQuantity(4L);

        when(productRepositoryImpl.save(any())).thenReturn(productToBeReturned);
        when(trackRepoImpl.findByProductName("Pepsi")).thenReturn(null);
        assertThrows(SaveProductDtoException.class,()-> productServiceImpl.addProduct(saveProductDto));
    }

    @Test
    @DisplayName("Remove products from the database")
    void removeProduct() throws InventoryException {
        ProductDto productDto = new ProductDto();
        productDto.setProductName("Pepsi");
        productDto.setPriceForEach(150.0);
        productDto.setQuantity(4);

        Product productToBeReturned = new Product();
        productToBeReturned.setProductName("Pepsi");
        productToBeReturned.setPriceForEach(150.0);
        productToBeReturned.setQuantity(4L);

        TrackingInfo infoToBeReturned  =  new TrackingInfo();
        infoToBeReturned.setProductName("Pepsi");
        infoToBeReturned.setTotalQuantity(100L);

        when(productRepositoryImpl.save(any())).thenReturn(productToBeReturned);
        when(trackRepoImpl.findByProductName("Pepsi")).thenReturn(infoToBeReturned);

        productServiceImpl.removeProduct(productDto);
    }

    @Test
    @DisplayName("Test that non existing products cannot be removed from the database")
    void removeNonExistingProduct(){
        ProductDto productDto = new ProductDto();
        productDto.setProductName("Pepsi");
        productDto.setPriceForEach(150.0);
        productDto.setQuantity(4);

        when(trackRepoImpl.findByProductName("Pepsi")).thenReturn(null);
        assertThrows(InventoryException.class,()->productServiceImpl.removeProduct(productDto));
    }

    @Test
    @DisplayName("Test that products cannot be removed if the quantity to be removed is greater than the quantity in the database")
    void removeMoreThanWhatIsInTheDataBase(){
        ProductDto productDto = new ProductDto();
        productDto.setProductName("Pepsi");
        productDto.setPriceForEach(150.0);
        productDto.setQuantity(4);

        TrackingInfo infoToBeReturned  =  new TrackingInfo();
        infoToBeReturned.setProductName("Pepsi");
        infoToBeReturned.setTotalQuantity(3L);

        when(trackRepoImpl.findByProductName("Pepsi")).thenReturn(infoToBeReturned);
        assertThrows(InventoryException.class,()->productServiceImpl.removeProduct(productDto));
    }

    @Test
    @DisplayName("Test that all products in the database can be viewed")
    void findAllProducts() throws InventoryException {
        List<Product> products =  new ArrayList<>();
        products.add(new Product());

        when(productRepositoryImpl.findAll()).thenReturn(products);

        List<Product> allProducts = productServiceImpl.findAllProducts();
        assertThat(allProducts).isNotEmpty();
        assertThat(allProducts).isNotNull();
    }

    @Test
    @DisplayName("Test that all products cannot be found when there's nothing in the database")
    void findAllProductsWhenTheDataBaseIsEmpty(){
        List<Product> products =  new ArrayList<>();

        when(productRepositoryImpl.findAll()).thenReturn(products);

        assertThrows(InventoryException.class,()-> productServiceImpl.findAllProducts());
    }
}