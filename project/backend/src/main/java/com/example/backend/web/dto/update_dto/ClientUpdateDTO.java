package com.example.backend.web.dto.update_dto;

import lombok.Data;

@Data
public class ClientUpdateDTO {
    private Long id;

    private String name;
    private String address;
    private String city;
    private String postalCode;

    private Long countryId;

}
