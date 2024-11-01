package com.example.backend.domain.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.backend.domain.model.model.Country;

public interface ICountryService {

    List<Country> getAllCountries();

    Page<Country> getAllCountriesWithPaginationAndSorting(PageRequest pageRequest);

    Country getCountryById(Long id);

    Country createCountry(Country country);

    Country updateCountry(Country country);

    void deleteCountryById(Long id);

}