package com.rewe.pharmacy.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Recipe extends BaseEntity {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "The date has to be in the past!")
    private LocalDate creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonIgnore
    private Doctor doctor;

    @ManyToMany
    @JoinTable(
            name = "medicines_recipes",
            joinColumns = { @JoinColumn(name = "recipe_id") },
            inverseJoinColumns = { @JoinColumn(name = "medicine_id") }
    )
    private Set<Medicine> medicines;
}
