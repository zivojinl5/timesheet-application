package com.example.backend.web.dto.response_dto;

import java.time.LocalDate;
import java.util.HashMap;


import lombok.Data;

@Data
public class MonthResponseDTO {
    private HashMap<LocalDate, Double> dateHoursMap;
    private double totalHours;

}
