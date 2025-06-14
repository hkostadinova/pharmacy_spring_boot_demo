package com.rewe.pharmacy.controller;

import com.rewe.pharmacy.data.entity.Medicine;
import com.rewe.pharmacy.dto.*;
import com.rewe.pharmacy.service.MedicineService;
import com.rewe.pharmacy.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/medicines")
@PreAuthorize("hasAuthority('seller')")
public class MedicineApiController {
    private final MedicineService medicineService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public List<MedicineDTO> getMedicines() {
        return this.medicineService.getMedicines();
    }

    @GetMapping("/{id}")
    public MedicineDTO getMedicine(@PathVariable long id) {
        return this.medicineService.getMedicine(id);
    }

    @PostMapping
    public MedicineDTO createMedicine(@RequestBody @Valid CreateMedicineDTO medicine, Authentication authentication) {

        authentication.getPrincipal();
        return mapperUtil.getModelMapper().map(this.medicineService
                .createMedicine(medicine), MedicineDTO.class);
    }
    @PutMapping("/{id}")
    public MedicineDTO updateMedicine(@RequestBody Medicine medicine, @PathVariable long id) {
        return this.medicineService.updateMedicine(medicine, id);
    }
    @GetMapping("/by-name/{name}")
    public List<MedicineDTO> findByName(@PathVariable String name) {
        return this.medicineService.findByName(name);
    }

    @GetMapping("/age-appropriateness/{ageAppropriateness}")
    public List<ResponseMedicineDTO> findResponseMedicineDTOsByAgeAppropriatenessGreaterThan(@PathVariable int ageAppropriateness) {
        return this.medicineService.findResponseMedicineDTOsByAgeAppropriatenessGreaterThan(ageAppropriateness);
    }

    @GetMapping("/group-by-age-appropriateness/{ageAppropriateness}")
    public List<CountMedicinesGroupByAgeAppropriateness> findNumberOfMedicinesGroupByAgeAppropriatenessHavingAgeAppropriatenessGreaterThan(@PathVariable int ageAppropriateness) {
        return this.medicineService.findNumberOfMedicinesGroupByAgeAppropriatenessHavingAgeAppropriatenessGreaterThan(ageAppropriateness);
    }

    @GetMapping("/recipes-needed-not-null")
    public List<Medicine> findByNeedsRecipeNotNull() {
        return this.medicineService.findByNeedsRecipeNotNull();
    }

    @GetMapping("/offset/{offset}/page-size/{pageSize}")
    public List<Medicine> findAllPageable(@PathVariable int pageSize, @PathVariable int offset) {
        return this.medicineService.findAllMedicines(offset, pageSize);
    }

    @GetMapping("/offset/{offset}/page-size/{pageSize}/field-name/{fieldName}")
    public List<Medicine> findAllPageable(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String fieldName) {
        return this.medicineService.findAllMedicines(offset, pageSize, fieldName);
    }

    @DeleteMapping("/{id}")
    public void deleteMedicine(@PathVariable long id) {
        this.medicineService.deleteMedicine(id);
    }

}


