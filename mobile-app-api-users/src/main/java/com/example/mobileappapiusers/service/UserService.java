package com.example.mobileappapiusers.service;

import com.example.mobileappapiusers.model.UserRest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    UserRest createUser(UserRest userRest);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    UserRest getUserDetailsByEmail(String email);
}
