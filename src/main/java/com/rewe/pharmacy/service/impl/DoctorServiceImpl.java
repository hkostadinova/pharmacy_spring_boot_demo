package com.rewe.pharmacy.service.impl;

import com.rewe.pharmacy.data.entity.Doctor;
import com.rewe.pharmacy.data.repository.DoctorRepository;
import com.rewe.pharmacy.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;

    @Override
    public List<Doctor> getDoctors() {
        return null;
    }

    @Override
    public Doctor getDoctor(long id) {
        return null;
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        return null;
    }

    @Override
    public Doctor updateDoctor(Doctor doctor, long id) {
        return null;
    }

    @Override
    public void deleteDoctor(long id) {

    }

    @Override
    public Doctor findDoctorByIdWithRecipes(long id) {
        return this.doctorRepository.findByDoctorIdWithRecipes(id);
    }
}
