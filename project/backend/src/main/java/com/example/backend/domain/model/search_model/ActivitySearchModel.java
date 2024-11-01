package com.example.backend.domain.model.search_model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActivitySearchModel {
    private Long userId;
    private LocalDate starDate;
    private LocalDate endDate;

}
