package com.example.backend.domain.model.update_model;

import lombok.Data;

@Data
public class ProjectUpdateModel {
    private Long id;

    private String name;
    private String description;

    private Long userId;

}
