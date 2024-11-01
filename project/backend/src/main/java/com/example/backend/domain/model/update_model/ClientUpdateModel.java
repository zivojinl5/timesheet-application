package com.example.backend.domain.model.update_model;

import lombok.Data;

@Data
public class ClientUpdateModel {
    private Long id;

    private String name;
    private String address;
    private String city;
    private String postalCode;

    private Long countryId;

}
