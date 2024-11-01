package com.example.backend.domain.model.create_model;

import lombok.Data;

@Data
public class ProjectCreateModel {
    private Long id;

    private String name;
    private String description;

    private Long clientId;
    private Long userId;

}
