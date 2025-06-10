package com.rewe.pharmacy.service;

import com.rewe.pharmacy.data.entity.Recipe;
import com.rewe.pharmacy.dto.SellMedicineDTO;

import java.util.List;

public interface SellingService {
    boolean sellMedicine(long medicineId, Recipe recipe);

    boolean sellMedicinesRecipes(List<SellMedicineDTO> sellMedicineRequests);

    boolean sellMedicines(SellMedicineDTO sellMedicineRequest);
}
