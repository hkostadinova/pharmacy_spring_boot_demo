package com.rewe.pharmacy.exception;

public class RecipeNotFoundException extends ObjectNotFoundException {
    public RecipeNotFoundException(String message, long id) {
        super(message, id);
    }
}
