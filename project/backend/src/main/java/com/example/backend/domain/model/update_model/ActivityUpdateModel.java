package com.example.backend.domain.model.update_model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ActivityUpdateModel {
    private Long id;

    private LocalDate date;
    private String description;
    private Double hours;
    private Double overtimeHours;

    private Long clientId;
    private Long projectId;
    private Long categoryId;
    private Long userId;

}