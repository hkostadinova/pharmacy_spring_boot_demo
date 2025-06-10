package com.rewe.pharmacy.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = InvalidMedicineNamesValidator.class)
public @interface InvalidMedicineNames {
    String message() default "Invalid medicine name. The word is reserved!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

