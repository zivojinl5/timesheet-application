package com.example.backend.web.dto.req_dto;

import com.example.backend.data.enums.Role;
import com.example.backend.data.enums.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {

  private String name;
  private String username;
  private String password;
  private String confirmPassword;
  private String email;
  private Double hours;
  private UserStatus userStatus;
  private Role role;
}

;