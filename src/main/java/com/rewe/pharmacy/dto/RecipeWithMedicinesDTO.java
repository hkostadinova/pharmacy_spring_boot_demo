package com.rewe.pharmacy.dto;

import jakarta.validation.constraints.Past;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeWithMedicinesDTO {
    private long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "The date has to be in the past!")
    private LocalDate creationDate;

    private Set<MedicineDTO> medicines;
}
