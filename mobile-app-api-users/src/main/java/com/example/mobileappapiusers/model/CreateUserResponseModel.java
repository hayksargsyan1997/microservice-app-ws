package com.example.mobileappapiusers.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserResponseModel {


    private String firstName;

    private String lastName;

    @Email
    private String email;
    private String userId;


}
