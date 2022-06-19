package de.vignold.vignoldmanagement.exception;

public class ProductAlreadyInProgressException extends RuntimeException{
    public ProductAlreadyInProgressException() {
        super("This product already in progress!");
    }
}
