package com.rewe.pharmacy.exception;


public class MedicineNotFoundException extends ObjectNotFoundException {
    public MedicineNotFoundException(String message, long id) {
        super(message, id);
    }
}
