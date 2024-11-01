package com.example.backend.web.dto.create_dto;

import lombok.Data;

@Data
public class ClientCreateDTO {
    private Long id;

    private String name;
    private String address;
    private String city;
    private String postalCode;

    private Long countryId;

}