package com.rewe.pharmacy.data.entity;

import com.rewe.pharmacy.validator.InvalidMedicineNames;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@NamedQuery(name = "Medicine.findByName", query = "SELECT m FROM Medicine m WHERE m.name = ?1")
@NamedQuery(name = "Medicine.findByNameAndAgeAppropriateness", query = "SELECT m FROM Medicine m " +
        "WHERE m.name = ?1 AND m.ageAppropriateness = ?2")
public class Medicine extends BaseEntity {

    @NotBlank
    @Size(min = 5, max = 20, message = "Min 5, Max 20")
    @InvalidMedicineNames
    private String name;

    @Min(value = 0, message = "Min 0")
    @Max(value = 18, message = "Max 18")
    private int ageAppropriateness;

    private boolean needsRecipe;

    private int quantity;
}
