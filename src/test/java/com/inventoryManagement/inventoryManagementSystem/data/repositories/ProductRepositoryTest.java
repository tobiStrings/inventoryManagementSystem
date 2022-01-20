package com.inventoryManagement.inventoryManagementSystem.data.repositories;

import com.inventoryManagement.inventoryManagementSystem.data.models.Product;
import com.inventoryManagement.inventoryManagementSystem.data.models.TrackingInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepositoryImpl;

    @Test
    void saveProduct(){
        Product productToSave =  new Product();
        productToSave.setProductName("Coke");
        productToSave.setPriceForEach(150.0);
        productToSave.setQuantity(1L);
        productToSave.setSavedTime(LocalDateTime.now());
        productToSave.getImageUrls().add("gfdstrewtretwr464");
        TrackingInfo productInfo = new TrackingInfo();
        productInfo.setEntryTime(LocalDateTime.now());
        productInfo.setTotalQuantity(1L);
        productToSave.setTrackInfo(productInfo);
        Product savedProduct = productRepositoryImpl.save(productToSave);
        assertThat(savedProduct.getId()).isNotNull();
        assertThat(savedProduct.getSavedTime()).isNotNull();
    }

    @Test
    void findProductById(){
        Product productToSave =  new Product();
        productToSave.setProductName("Pepsi");
        productToSave.setPriceForEach(150.0);
        productToSave.setQuantity(1L);
        productToSave.setSavedTime(LocalDateTime.now());
        productToSave.getImageUrls().add("gfdstrewtretwr464");
        Product savedProduct = productRepositoryImpl.save(productToSave);
        assertThat(savedProduct.getId()).isNotNull();
        assertThat(savedProduct.getSavedTime()).isNotNull();

        Optional<Product> foundProduct = productRepositoryImpl.findById(savedProduct.getId());
        assertThat(foundProduct).isPresent();
        assertThat(foundProduct.get()).isNotNull();
    }

    @Test
    void findProductsByTheirNames(){
        Optional<List<Product>> foundProducts = productRepositoryImpl.findByProductName("Pepsi");
        assertThat(foundProducts).isPresent();
        assertThat(foundProducts.get()).isNotNull();
        assertThat(foundProducts.get()).hasSize(1);
        assertThat(foundProducts.get()).hasSize(1);

    }

    @Test
    void findAllProducts(){
        List<Product> allProducts = productRepositoryImpl.findAll();
        assertThat(allProducts).isNotNull();
    }

    @Test
    void deleteProductById(){
        Product productToSave =  new Product();
        productToSave.setProductName("FreshYo");
        productToSave.setPriceForEach(150.0);
        productToSave.setQuantity(1L);
        productToSave.setSavedTime(LocalDateTime.now());
        productToSave.getImageUrls().add("gfdstrewtretwr464");
        Product savedProduct = productRepositoryImpl.save(productToSave);
        assertThat(savedProduct.getId()).isNotNull();
        assertThat(savedProduct.getSavedTime()).isNotNull();

        Optional<Product> foundProduct = productRepositoryImpl.findById(savedProduct.getId());
        assertThat(foundProduct).isPresent();
        assertThat(foundProduct.get()).isNotNull();

        productRepositoryImpl.deleteById(foundProduct.get().getId());

        Optional<Product> emptyProduct = productRepositoryImpl.findById(savedProduct.getId());
        assertThat(emptyProduct).isNotPresent();
    }

    @Test
    void deleteProductByProductName(){
        Product productToSave =  new Product();
        productToSave.setProductName("FreshYo");
        productToSave.setPriceForEach(150.0);
        productToSave.setQuantity(1L);
        productToSave.setSavedTime(LocalDateTime.now());
        productToSave.getImageUrls().add("gfdstrewtretwr464");
        Product savedProduct = productRepositoryImpl.save(productToSave);
        assertThat(savedProduct.getId()).isNotNull();
        assertThat(savedProduct.getSavedTime()).isNotNull();

        Optional<List<Product>> foundProduct = productRepositoryImpl.findByProductName(savedProduct.getProductName());
        assertThat(foundProduct).isPresent();
        assertThat(foundProduct.get()).isNotNull();
        assertThat(foundProduct.get()).hasSize(1);

        productRepositoryImpl.deleteByProductName(savedProduct.getProductName());

        Optional<List<Product>> emptyProduct = productRepositoryImpl.findByProductName(savedProduct.getProductName());
        assertThat(emptyProduct.isPresent()).isTrue();
        assertThat(emptyProduct.get()).hasSize(0);
    }

    @Test
    void deleteAllProducts(){
        productRepositoryImpl.deleteAll();
        assertThat(productRepositoryImpl.findAll()).hasSize(0);
        assertThat(productRepositoryImpl.findAll()).isEmpty();
    }
}