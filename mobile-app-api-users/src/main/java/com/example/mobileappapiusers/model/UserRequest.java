package com.example.mobileappapiusers.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRequest {
    @NotNull(message = "First name cannot be null")
    private String firstName;
    @NotNull(message = "lastname name cannot be null")
    private String lastName;
    @NotNull(message = "email name cannot be null")
    @Email
    private String email;
    @NotNull(message = "password name cannot be null")
    @Size(min = 8, max = 16, message = "jsjhds")
    private String password;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
