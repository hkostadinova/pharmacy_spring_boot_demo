package com.rewe.pharmacy.dto;

import com.rewe.pharmacy.data.entity.Recipe;

import java.util.List;

public class SellMedicineDTO {

    private List<Long> medicineIds;

    private Recipe recipe;

    public List<Long> getMedicineIds() {
        return medicineIds;
    }

    public Recipe getRecipe() {
        return recipe;
    }
}
