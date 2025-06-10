package com.rewe.pharmacy.service;

import com.rewe.pharmacy.data.entity.Medicine;
import com.rewe.pharmacy.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface MedicineService {
    List<MedicineDTO> getMedicines();
    MedicineDTO getMedicine(long id);
    CreateMedicineDTO createMedicine(CreateMedicineDTO medicine);
    MedicineDTO updateMedicine(Medicine medicine, long id);
    List<MedicineDTO> findByName(String name);
    List<ResponseMedicineDTO> findResponseMedicineDTOsByAgeAppropriatenessGreaterThan(int ageAppropriateness);
    List<CountMedicinesGroupByAgeAppropriateness>findNumberOfMedicinesGroupByAgeAppropriatenessHavingAgeAppropriatenessGreaterThan(int ageAppropriateness);
    List<Medicine> findByNeedsRecipeNotNull();
    List<Medicine> findAllMedicines(Pageable pageable);
    List<Medicine> findAllMedicines(int offset, int pageSize);
    List<Medicine> findAllMedicines(int offset, int pageSize, String fieldName);
    void deleteMedicine(long id);
}
