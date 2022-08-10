package com.example.mobileappapiusers.controller;

import com.example.mobileappapiusers.model.UserRequest;
import com.example.mobileappapiusers.model.UserRest;
import com.example.mobileappapiusers.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private Environment env;

    @GetMapping("/status/check")
    public String status() {
        return "Working on port " + env.getProperty("local.server.port");
    }


    @PostMapping("/save")
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserRequest userRequest){
        logger.info("fghjaksdfgh");
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserRest userRest = modelMapper.map(userRequest, UserRest.class);
        userService.createUser(userRest);
        System.out.println(userRest.getFirstName());
        if(userRest==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userRest,HttpStatus.OK);
    }


}
