package com.example.backend.domain.model.create_model;

import lombok.Data;

@Data
public class ClientCreateModel {
    private Long id;

    private String name;
    private String address;
    private String city;
    private String postalCode;

    private Long countryId;

}
