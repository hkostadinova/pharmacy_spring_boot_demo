package com.rewe.pharmacy.data.repository;


import com.rewe.pharmacy.data.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
