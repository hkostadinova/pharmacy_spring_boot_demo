package com.rewe.pharmacy.service;

import com.rewe.pharmacy.dto.CreateMedicineDTO;
import com.rewe.pharmacy.dto.MedicineDTO;

import java.util.List;

public interface MedicineService {
    List<MedicineDTO> getMedicines();
    MedicineDTO getMedicine(long id);
    CreateMedicineDTO createMedicine(CreateMedicineDTO medicine);
}
