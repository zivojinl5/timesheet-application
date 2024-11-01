package com.example.backend.domain.domain_repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.backend.domain.model.model.Country;

public interface ICountryDomainRepository {
    Page<Country> findAll(PageRequest pageRequest);

    List<Country> findAll();

    Country findById(Long id);

    Country save(Country country);

    Country update(Country country);

    void deleteById(Long id);

}