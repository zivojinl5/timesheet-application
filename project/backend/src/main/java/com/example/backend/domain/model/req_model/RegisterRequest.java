package com.example.backend.domain.model.req_model;

import com.example.backend.data.enums.Role;
import com.example.backend.data.enums.UserStatus;

import lombok.Data;

@Data
public class RegisterRequest {

    private String name;
    private String username;
    private String password;
    private String email;
    private Double hours;
    private UserStatus userStatus;
    private Role role;
}
