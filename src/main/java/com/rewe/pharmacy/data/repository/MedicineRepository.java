package com.rewe.pharmacy.data.repository;

import com.rewe.pharmacy.data.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    List<Medicine> findByName(String name);
    List<Medicine> findByNameStartsWith(String name);
    List<Medicine> findByNameStartsWithAndAgeAppropriatenessGreaterThan(String name, int age);
    List<Medicine> findByAgeAppropriatenessGreaterThanAndNeedsRecipe(int age, boolean needsRecipe);
//    @Query(name="SELECT new MedicineDTO()")
//    List<MedicineDTO> findMedicineDto();
}
