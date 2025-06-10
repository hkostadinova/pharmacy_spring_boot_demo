package com.rewe.pharmacy.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;
import java.util.TreeSet;

public class InvalidMedicineNamesValidator implements ConstraintValidator<InvalidMedicineNames, String> {
    private final Set<String> reservedWord = new TreeSet<>();
    @Override
    public void initialize(InvalidMedicineNames constraintAnnotation) {
        reservedWord.addAll(Set.of("Medicine", "Medication ", "Drug"));
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return reservedWord.stream().noneMatch(value::contains);
    }
}