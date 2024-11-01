package com.example.backend.web.dto.update_dto;

import lombok.Data;

@Data
public class ProjectUpdateDTO {
    private Long id;

    private String name;
    private String description;

    private Long userId;

}
