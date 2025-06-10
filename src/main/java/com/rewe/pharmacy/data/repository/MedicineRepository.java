package com.rewe.pharmacy.data.repository;

import com.rewe.pharmacy.data.entity.Medicine;
import com.rewe.pharmacy.dto.CountMedicinesGroupByAgeAppropriateness;
import com.rewe.pharmacy.dto.ResponseMedicineDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    List<Medicine> findByName(String name);

    List<Medicine> findByNameStartsWith(String name);

    List<Medicine> findByNameAndAgeAppropriateness(String name, int ageAppropriateness);

    List<Medicine> findByNameStartsWithAndAgeAppropriatenessGreaterThan(String name, int age);

    List<Medicine> findByAgeAppropriatenessGreaterThanAndNeedsRecipe(int age, boolean needsRecipe);

    @Query(value = "SELECT m.name, m.ageAppropriateness FROM Medicine m WHERE m.ageAppropriateness > :ageAppropriateness")
    List<ResponseMedicineDTO> findResponseMedicineDTOsByAgeAppropriatenessGreaterThan(int ageAppropriateness);

    @Query(value = "SELECT m.name, m.ageAppropriateness FROM medicine m WHERE m.age_appropriateness > :age_appropriateness", nativeQuery = true)
    List<ResponseMedicineDTO> findNativeResponseMedicineDTOsByAgeAppropriatenessGreaterThan(@Param("ageAppropriateness") int ageAppropriateness);

    @Query(value = "SELECT m.ageAppropriateness AS ageAppropriateness, COUNT(m.id) AS countMedicines" +
            " FROM Medicine m GROUP BY m.ageAppropriateness HAVING m.ageAppropriateness > :ageAppropriateness")
    List<CountMedicinesGroupByAgeAppropriateness> findNumberOfMedicinesGroupByAgeAppropriatenessHavingAgeAppropriatenessGreaterThan(int ageAppropriateness);

    @Query(value = "SELECT new com.rewe.pharmacy.dto.MedicineAgeAppropriatenessDTO(m.ageAppropriateness, COUNT(m.id))" +
            " FROM Medicine m GROUP BY m.ageAppropriateness HAVING m.ageAppropriateness > :ageAppropriateness")
    List<CountMedicinesGroupByAgeAppropriateness> findNumberOfMedicinesGroupByAgeAppropriatenessHavingAgeAppropriatenessGreaterThan_2(int ageAppropriateness);
    List<Medicine> findByNeedsRecipeNotNull();
}
