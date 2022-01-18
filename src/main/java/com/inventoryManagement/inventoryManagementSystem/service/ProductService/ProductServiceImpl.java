package com.inventoryManagement.inventoryManagementSystem.service.ProductService;

import com.inventoryManagement.inventoryManagementSystem.data.enums.Operation;
import com.inventoryManagement.inventoryManagementSystem.data.models.Product;
import com.inventoryManagement.inventoryManagementSystem.data.models.TrackingInfo;
import com.inventoryManagement.inventoryManagementSystem.data.repositories.ProductRepository;
import com.inventoryManagement.inventoryManagementSystem.data.repositories.TrackingInfoRepo;
import com.inventoryManagement.inventoryManagementSystem.service.dtos.ProductDto;
import com.inventoryManagement.inventoryManagementSystem.service.exceptions.InventoryException;
import com.inventoryManagement.inventoryManagementSystem.service.exceptions.SaveProductDtoException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Transactional
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepositoryImpl;
    @Autowired
    private TrackingInfoRepo trackInfoServiceImpl;
//    private  TrackingInfoService trackInfoServiceImpl ;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public Product addProduct(ProductDto productToSave) throws SaveProductDtoException {
//        trackInfoServiceImpl = new TrackingInfoServiceImpl();
        validateSaveProductDto(productToSave);

        TrackingInfo trackInfo = trackInfoServiceImpl.findByProductName(productToSave.getProductName());

        if (trackInfo == null){
            trackInfo = new TrackingInfo();
            trackInfo.setProductName(productToSave.getProductName());
            trackInfo.setTotalQuantity(0L);
        }
        trackInfo.setTotalQuantity(trackInfo.getTotalQuantity()+productToSave.getQuantity());
        trackInfo.setEntryTime(LocalDateTime.now());
        trackInfoServiceImpl.save(trackInfo);

        Product product =  new Product();
        mapper.map(productToSave,product);
        return productRepositoryImpl.save(product);
    }

    private void validateSaveProductDto(ProductDto productDto) throws SaveProductDtoException {
        if (productDto.getPriceForEach() <= 0.0){
            throw new SaveProductDtoException("Product cannot be saved because, Price for each product cannot be zero or less than zero");
        }

        if (productDto.getQuantity() <=0){
            throw new SaveProductDtoException("Product not saved because, Product quantity cannot be less than or equals zero");
        }

        if (productDto.getProductName() == null || productDto.getProductName().isEmpty() || productDto.getProductName().isBlank()){
            throw new SaveProductDtoException("Product not saved because, Product name is empty.");
        }

        if (productDto.getOperation() == null || productDto.getOperation().isEmpty() || productDto.getOperation().isBlank()){
            throw new SaveProductDtoException("Product not saved because, Operation type is empty.");
        }else if (productDto.getOperation() != null){
            productDto.setOperation(productDto.getOperation().toUpperCase());
        }

        if (productDto.getImageUrls().isEmpty()){
            throw new SaveProductDtoException("Product not saved because, At least one image should be provided");
        }
    }

    @Override
    public Product removeProduct(ProductDto productDto) throws InventoryException {
        TrackingInfo trackInfo = trackInfoServiceImpl.findByProductName(productDto.getProductName());
        if (trackInfo == null){
            throw new InventoryException("Product does not exist");
        }
        if (trackInfo.getTotalQuantity() < productDto.getQuantity()){
            throw new InventoryException("The products available is less than the item demanded for. The remaining products is "+trackInfo.getTotalQuantity());
        }
        trackInfo.setTotalQuantity(trackInfo.getTotalQuantity() - productDto.getQuantity());
        trackInfoServiceImpl.save(trackInfo);

        Product product = new Product();
        mapper.map(productDto,product);
        product.setOperation(Operation.DELETE);

        productRepositoryImpl.save(product);

        return product;
    }

    @Override
    public List<Product> findAllProducts() throws InventoryException {
        List<Product> allProducts = productRepositoryImpl.findAll();
        if (!allProducts.isEmpty()){
            return  allProducts;
        }
        throw new InventoryException("There are no products in the database");
    }
}
