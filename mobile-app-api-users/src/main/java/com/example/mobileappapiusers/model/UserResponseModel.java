package com.example.mobileappapiusers.model;

import com.example.mobileappapiusers.model.AlbumResponseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseModel {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private List<AlbumResponseModel> albumResponseModels;
}
