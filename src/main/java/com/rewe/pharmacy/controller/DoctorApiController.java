package com.rewe.pharmacy.controller;

import com.rewe.pharmacy.data.entity.Doctor;
import com.rewe.pharmacy.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doctor")
public class DoctorApiController {
    private final DoctorService doctorService;
    @GetMapping("/{id}")
    public Doctor findDoctorByIdWithRecipes(@PathVariable long id) {
        return this.doctorService.findDoctorByIdWithRecipes(id);
    }
}
