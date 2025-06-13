package com.rewe.pharmacy.controller;

import com.rewe.pharmacy.dto.UserDTO;
import com.rewe.pharmacy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;
    @GetMapping("/{username}")
    public UserDTO findUserByUsername(@PathVariable String username) {
        return this.userService.findByUsername(username);
    }
}
