package com.rewe.pharmacy.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException {
    private final long id;

    public ObjectNotFoundException(String message, long id) {
        super(message);
        this.id = id;
    }
}
