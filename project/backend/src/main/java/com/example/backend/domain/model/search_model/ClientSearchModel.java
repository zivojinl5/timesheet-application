package com.example.backend.domain.model.search_model;

import org.springframework.data.domain.PageRequest;

import lombok.Data;

@Data
public class ClientSearchModel {
    private String startingLetter;
    private String searchString;
    private PageRequest pageRequest;
}
