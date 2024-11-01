package com.example.backend.domain.model.model;

import com.example.backend.data.enums.Role;
import com.example.backend.data.enums.UserStatus;

import lombok.Data;

@Data
public class User {
    private Long id;

    private String name;
    private String username;
    private String password;
    private String email;
    private Double hours;

    private Role role;
    private UserStatus userStatus;

}
