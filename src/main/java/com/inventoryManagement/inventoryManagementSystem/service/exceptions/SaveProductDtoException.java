package com.inventoryManagement.inventoryManagementSystem.service.exceptions;

public class SaveProductDtoException extends InventoryException{
    public SaveProductDtoException() {
    }

    public SaveProductDtoException(String message) {
        super(message);
    }

    public SaveProductDtoException(Throwable cause) {
        super(cause);
    }
}
