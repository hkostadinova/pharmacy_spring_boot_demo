package com.rewe.pharmacy.controller;

import com.rewe.pharmacy.dto.CreateMedicineDTO;
import com.rewe.pharmacy.dto.MedicineDTO;
import com.rewe.pharmacy.service.MedicineService;
import com.rewe.pharmacy.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/medicines")
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
    public MedicineDTO createMedicine(@RequestBody CreateMedicineDTO medicine) {
        return mapperUtil.getModelMapper().map(this.medicineService
                .createMedicine(medicine), MedicineDTO.class);
    }
}


