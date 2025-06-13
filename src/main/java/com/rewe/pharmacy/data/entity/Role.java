package com.rewe.pharmacy.data.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="role")
public class Role extends BaseEntity implements GrantedAuthority {
    private String authority;
    @ManyToMany
    private Set<User> users;
    @Override
    public String getAuthority() {
        return this.authority;
    }
}
