package com.rewe.pharmacy.service.impl;

import com.rewe.pharmacy.data.entity.Recipe;
import com.rewe.pharmacy.dto.CreateMedicineDTO;
import com.rewe.pharmacy.dto.SellMedicineDTO;
import com.rewe.pharmacy.service.MedicineService;
import com.rewe.pharmacy.service.RecipeService;
import com.rewe.pharmacy.service.SellingService;
import com.rewe.pharmacy.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SellingServiceImpl implements SellingService {

    private final MedicineService medicineService;

    private final RecipeService recipeService;
    private final MapperUtil mapperUtil;

    @Override
    @Transactional
            (propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,
            rollbackFor = {RuntimeException.class}, noRollbackFor = {IllegalArgumentException.class})
    public boolean sellMedicine(long medicineId, Recipe recipe) {
        // Register the recipe
        Recipe createdRecipe = this.recipeService.createRecipe(recipe);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (createdRecipe.getCreationDate().compareTo(LocalDate.now()) == 1) {
            throw new RuntimeException("Invalid Recipe Creation Date!");
        }

        // Reducing the quantity of the medicine
        CreateMedicineDTO medicine = mapperUtil.getModelMapper().map(medicineService.getMedicine(medicineId), CreateMedicineDTO.class);
        medicine.setQuantity(medicine.getQuantity() - 1);
        medicineService.createMedicine(medicine);
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean sellMedicines(SellMedicineDTO sellMedicineRequest) {
        // Register the recipe
        Recipe createdRecipe = this.recipeService.createRecipe(sellMedicineRequest.getRecipe());

        // Reducing the quantity of the medicines
        sellMedicineRequest
                .getMedicineIds()
                .stream()
                .forEach(medicineId -> this.medicineService.getMedicine(medicineId)
                        .setQuantity(this.medicineService.getMedicine(medicineId).getQuantity() - 1));
        return true;
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean sellMedicinesRecipes(List<SellMedicineDTO> sellMedicineRequests) {
        // Selling medicines in a list of recipes
        sellMedicineRequests
                .stream()
                .forEach(sellMedicineRequest -> this.sellMedicines(sellMedicineRequest));
        return true;
    }
}
