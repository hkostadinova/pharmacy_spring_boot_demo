package com.rewe.pharmacy.data.repository;


import com.rewe.pharmacy.data.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query(value = "SELECT d from Doctor d JOIN FETCH d.recipes WHERE d.id=?1")
    Doctor findByDoctorIdWithRecipes(long id);
}
