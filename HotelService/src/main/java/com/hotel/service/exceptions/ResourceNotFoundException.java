package com.hotel.service.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String hotelNotFound) {
        super(hotelNotFound);
    }

    public ResourceNotFoundException() {
        super("Resource not found");
    }

}
