package com.rewe.pharmacy.service;

import com.rewe.pharmacy.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    UserDTO findByUsername(String username) throws UsernameNotFoundException;
}
