package com.inventoryManagement.inventoryManagementSystem.service.exceptions;

public class InventoryException extends Exception{
    public InventoryException() {
    }

    public InventoryException(String message) {
        super(message);
    }

    public InventoryException(Throwable cause) {
        super(cause);
    }
}
