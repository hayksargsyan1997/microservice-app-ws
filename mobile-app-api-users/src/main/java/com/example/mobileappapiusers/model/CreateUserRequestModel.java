package com.example.mobileappapiusers.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequestModel {

    @NotNull(message = "First name can't be null")
    private String firstName;
    @NotNull(message = "Last name can't be null")
    private String lastName;
    @NotNull(message = "Email can't be null")
    @Email
    private String email;
    @NotNull(message = "Password can't be null")
    @Size(min = 8,max = 16,message = "Password must be equal or grater than 8 characters and less than 16 characters")
    private String password;


}
