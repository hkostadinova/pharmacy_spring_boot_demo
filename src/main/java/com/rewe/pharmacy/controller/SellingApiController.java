package com.rewe.pharmacy.controller;

import com.rewe.pharmacy.data.entity.Recipe;
import com.rewe.pharmacy.dto.SellMedicineDTO;
import com.rewe.pharmacy.service.SellingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sell")
public class SellingApiController {

    private final SellingService sellingService;

    public SellingApiController(SellingService sellingService) {
        this.sellingService = sellingService;
    }

    @PostMapping("/{medicineId}")
    public boolean sellMedicine(@PathVariable long medicineId, @RequestBody Recipe recipe) {
        return this.sellingService.sellMedicine(medicineId, recipe);
    }

    @PostMapping("/medicines")
    public boolean sellMedicines(@RequestBody SellMedicineDTO sellMedicineRequest) {
        return this.sellingService.sellMedicines(sellMedicineRequest);
    }

    @PostMapping("/medicines-recipes")
    public boolean sellMedicinesRecipes(@RequestBody List<SellMedicineDTO> sellMedicineRequests) {
        return this.sellingService.sellMedicinesRecipes(sellMedicineRequests);
    }
}
