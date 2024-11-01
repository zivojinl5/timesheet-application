package com.example.backend.web.dto.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ActivityDTO {
    private Long id;

    private LocalDate date;
    private String description;
    private Double hours;
    private Double overtimeHours;

    private ClientDTO client;
    private ProjectDTO project;
    private CategoryDTO category;
    private UserDTO user;
}