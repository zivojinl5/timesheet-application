package com.example.backend.web.dto.dto;

import lombok.Data;

@Data
public class ProjectDTO {
    private Long id;

    private String name;
    private String description;

    private ClientDTO client;
    private UserDTO user;

}
