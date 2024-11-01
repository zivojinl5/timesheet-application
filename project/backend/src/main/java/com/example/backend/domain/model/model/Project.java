package com.example.backend.domain.model.model;

import lombok.Data;

@Data
public class Project {
    private Long id;

    private String name;
    private String description;

    private Client customer;
    private User lead;

}
