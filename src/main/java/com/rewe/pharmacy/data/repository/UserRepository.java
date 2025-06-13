package com.rewe.pharmacy.data.repository;

import com.rewe.pharmacy.data.entity.User;
import com.rewe.pharmacy.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value="SELECT u FROM User u JOIN FETCH u.authorities WHERE u.username= :username")
    User findByUsername(String username);
    @Query(value="SELECT u.username, u.accountNonExpired, u.accountNonLocked, u.credentialsNonExpired, u.enabled FROM User u WHERE u.username= :username")
    UserDTO findUserByUsername(String username);
}
