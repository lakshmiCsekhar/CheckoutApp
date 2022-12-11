package org.ecommerce.exception;

public class ServiceFailedException extends Exception{

    public ServiceFailedException() {
        super();
    }

    public ServiceFailedException(String message) {
        super(message);
    }

    public ServiceFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
