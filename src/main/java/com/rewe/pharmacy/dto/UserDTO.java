package com.rewe.pharmacy.dto;

import com.rewe.pharmacy.data.entity.Role;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;

import java.util.Set;

public record UserDTO(
        String username,
        boolean accountNonExpired,
        boolean accountNonLocked,
        boolean credentialsNonExpired,
        boolean enabled) {

}
