package com.example.backend.domain.model.model;

import lombok.Data;

@Data
public class Client {
    private Long id;

    private String name;
    private String address;
    private String city;
    private String postalCode;

    private Country country;

}
