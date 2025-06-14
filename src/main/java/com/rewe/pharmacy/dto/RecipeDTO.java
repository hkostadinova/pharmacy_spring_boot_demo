package com.rewe.pharmacy.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RecipeDTO {

    private long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "The date has to be in the past!")
    private LocalDate creationDate;
}
