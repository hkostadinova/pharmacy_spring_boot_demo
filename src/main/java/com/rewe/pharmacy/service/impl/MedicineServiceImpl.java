package com.rewe.pharmacy.service.impl;


import com.rewe.pharmacy.data.entity.Medicine;
import com.rewe.pharmacy.data.repository.MedicineRepository;
import com.rewe.pharmacy.dto.CreateMedicineDTO;
import com.rewe.pharmacy.dto.MedicineDTO;
import com.rewe.pharmacy.service.MedicineService;
import com.rewe.pharmacy.util.MapperUtil;
import lombok.RequiredArgsConstructor;
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


}
