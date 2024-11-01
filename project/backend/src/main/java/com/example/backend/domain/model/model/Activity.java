package com.example.backend.domain.model.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Activity {
    private Long id;

    private LocalDate date;
    private String description;
    private Double hours;
    private Double overtimeHours;

    private Client client;
    private Project project;
    private Category category;
    private User user;

}
