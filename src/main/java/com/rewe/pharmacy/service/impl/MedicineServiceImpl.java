package com.rewe.pharmacy.service.impl;


import com.rewe.pharmacy.data.entity.Medicine;
import com.rewe.pharmacy.data.repository.MedicineRepository;
import com.rewe.pharmacy.dto.*;
import com.rewe.pharmacy.service.MedicineService;
import com.rewe.pharmacy.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;
    private final MapperUtil mapperUtil;

    @Override
    public List<MedicineDTO> getMedicines() {
        return
                this.mapperUtil
                        .mapList(
                                this.medicineRepository.findAll(), MedicineDTO.class);
    }

    @Override
    @Cacheable(cacheNames = "medicines", key = "#id")
    public MedicineDTO getMedicine(long id) {
        return
                this.mapperUtil.getModelMapper().map(
                        this.medicineRepository
                                .findById(id).orElseThrow(()
                                        -> new RuntimeException("Medicine with id=" + id + " not found!")),
                        MedicineDTO.class);
    }

    @Override
    public CreateMedicineDTO createMedicine(CreateMedicineDTO medicine) {
        return mapperUtil.getModelMapper()
                .map(this.medicineRepository
                        .save(mapperUtil.getModelMapper()
                                .map(medicine, Medicine.class)), CreateMedicineDTO.class);
    }

    @Override
    @CachePut(cacheNames = "medicines", key = "#id")
    public MedicineDTO updateMedicine(Medicine medicine, long id) {
        return mapperUtil.getModelMapper().map(this.medicineRepository.save(medicine), MedicineDTO.class);
    }

    @Override
    public List<MedicineDTO> findByName(String name) {
        return
                this.mapperUtil
                        .mapList(
                                this.medicineRepository.findByName(name), MedicineDTO.class);
    }

    @Override
    public List<ResponseMedicineDTO> findResponseMedicineDTOsByAgeAppropriatenessGreaterThan(int ageAppropriateness) {
        return this.medicineRepository.findResponseMedicineDTOsByAgeAppropriatenessGreaterThan(ageAppropriateness);
    }

    @Override
    public List<CountMedicinesGroupByAgeAppropriateness> findNumberOfMedicinesGroupByAgeAppropriatenessHavingAgeAppropriatenessGreaterThan(int ageAppropriateness) {
        return this.medicineRepository.findNumberOfMedicinesGroupByAgeAppropriatenessHavingAgeAppropriatenessGreaterThan_2(ageAppropriateness);
    }

    @Override
    public List<Medicine> findByNeedsRecipeNotNull() {
        return this.medicineRepository.findAll();
    }

    @Override
    public List<Medicine> findAllMedicines(Pageable pageable) {
        return medicineRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Medicine> findAllMedicines(int offset, int pageSize) {
        Pageable pageable = PageRequest.ofSize(pageSize);
        if (offset > 0) {
            pageable = pageable.withPage(offset / pageSize);
        } else {
            pageable = pageable.withPage(0);
        }
        return this.findAllMedicines(pageable);
    }

    @Override
    public List<Medicine> findAllMedicines(int offset, int pageSize, String fieldName) {
        Pageable pageable = PageRequest.of(offset, pageSize, Sort.by(fieldName));
        if (offset > 0) {
            pageable = pageable.withPage(offset / pageSize);
        } else {
            pageable = pageable.withPage(0);
        }

        return this.findAllMedicines(pageable);
    }

    @Override
    @CacheEvict(cacheNames = "medicines", key = "#id")
    public void deleteMedicine(long id) {
        this.medicineRepository.deleteById(id);
    }

}
