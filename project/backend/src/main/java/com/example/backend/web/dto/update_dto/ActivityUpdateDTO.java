package com.example.backend.web.dto.update_dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ActivityUpdateDTO {
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