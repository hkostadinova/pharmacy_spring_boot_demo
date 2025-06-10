package com.rewe.pharmacy.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MedicineAgeAppropriatenessDTO {
    private int ageAppropriateness;
    private long countMedicines;
}
