package com.example.mobileappapiusers.controller;

import com.example.mobileappapiusers.controller.dto.UserDto;
import com.example.mobileappapiusers.model.CreateUserRequestModel;
import com.example.mobileappapiusers.model.CreateUserResponseModel;
import com.example.mobileappapiusers.model.UserResponseModel;
import com.example.mobileappapiusers.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final Environment environment;

    @Autowired
    public UserController(Environment environment, UserService userService) {
        this.environment = environment;
        this.userService = userService;
    }

    @GetMapping("/status/check")
    public String status() {
        return "working on port " + environment.getProperty("local.server.port")+ ", token = " + environment.getProperty("token.secret");
    }

    @PostMapping(value = "/save",
            consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CreateUserResponseModel> createUser(@RequestBody CreateUserRequestModel requestModel) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = modelMapper.map(requestModel, UserDto.class);

        UserDto createdUser = userService.createUser(userDto);
        CreateUserResponseModel responseModel = modelMapper.map(createdUser, CreateUserResponseModel.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
    }

    @GetMapping(value = "/{userId}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserResponseModel> getUser(@PathVariable ("userId") String userId){

        UserDto userDto=userService.getUserByUserId(userId);
        UserResponseModel responseModel=new ModelMapper().map(userDto,UserResponseModel.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseModel);
    }
}
