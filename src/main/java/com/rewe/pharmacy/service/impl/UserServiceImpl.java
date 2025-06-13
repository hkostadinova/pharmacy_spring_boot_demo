package com.rewe.pharmacy.service.impl;

import com.rewe.pharmacy.data.entity.User;
import com.rewe.pharmacy.dto.UserDTO;
import com.rewe.pharmacy.data.repository.UserRepository;
import com.rewe.pharmacy.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public UserDTO findByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findUserByUsername(username);
    }
}
