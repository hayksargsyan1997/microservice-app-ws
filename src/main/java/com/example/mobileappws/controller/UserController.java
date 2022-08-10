package com.example.mobileappws.controller;

import com.example.mobileappws.model.UserRequest;
import com.example.mobileappws.model.UserRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {

    Map<String, UserRest> users;


    @GetMapping(path = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
        if (users.containsKey(userId)) {
            return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    @GetMapping
    public String getUsers(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
                           @RequestParam(value = "limit", defaultValue = "50") int limit) {

        return "get user was called with page = " + page + " limit = " + limit;
    }

    @PostMapping(consumes =
            {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE},
            produces =
                    {MediaType.APPLICATION_JSON_VALUE,
                            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserRequest userRequest) {
        UserRest userRest = new UserRest();
        userRest.setFirstName(userRequest.getFirstName());
        userRest.setLastName(userRequest.getLastName());
        userRest.setEmail(userRequest.getEmail());
        String userId = UUID.randomUUID().toString();
        userRest.setUserId(userId);

        if (users == null) {
            users = new HashMap<>();
            users.put(userId, userRest);
        }

        return new ResponseEntity<>(userRest, HttpStatus.OK);
    }

    @PutMapping
    public String updateUser() {
        return "update user was called";
    }

    @DeleteMapping
    public String deleteUser() {
        return "delete user was called";
    }

}
