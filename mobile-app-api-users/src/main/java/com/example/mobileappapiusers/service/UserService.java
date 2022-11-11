package com.example.mobileappapiusers.service;

import com.example.mobileappapiusers.controller.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);
    UserDto getUserDetailsByEmail(String email);

    UserDto getUserByUserId(String userId);
}
