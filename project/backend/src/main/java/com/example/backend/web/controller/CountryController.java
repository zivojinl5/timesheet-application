package com.example.backend.web.controller;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.domain.model.model.Country;
import com.example.backend.domain.service.ICountryService;
import com.example.backend.mapper.GenericMapper;
import com.example.backend.web.dto.create_dto.CountryCreateDTO;
import com.example.backend.web.dto.dto.CountryDTO;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/countries")
@CrossOrigin
public class CountryController {
    private final ICountryService countryService;
    private final GenericMapper genericMapper;

    @GetMapping("/pagination/{pageNumber}/{pageSize}/{sortingProperty}/{sortingDirection}")
    public ResponseEntity<Page<CountryDTO>> getAllCountriesWithPaginationAndSorting(@PathVariable int pageNumber,
            @PathVariable int pageSize, @PathVariable String sortingProperty, @PathVariable String sortingDirection) {
        Direction direction = sortingDirection.equals("ASC") ? Direction.ASC : Direction.DESC;
        Sort sort = Sort.by(direction, sortingProperty);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        Page<Country> countryPage = countryService.getAllCountriesWithPaginationAndSorting(pageRequest);
        Page<CountryDTO> countryDTOPage = genericMapper.mapPage(countryPage, CountryDTO.class);
        ResponseEntity<Page<CountryDTO>> responseEntity = ResponseEntity.ok(countryDTOPage);
        return responseEntity;
    }

    @GetMapping
    public ResponseEntity<List<CountryDTO>> getAllCountries() {
        List<Country> countries = countryService.getAllCountries();
        List<CountryDTO> countryDTOs = genericMapper.mapList(countries, CountryDTO.class);
        ResponseEntity<List<CountryDTO>> responseEntity = ResponseEntity.ok(countryDTOs);
        return responseEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDTO> getCountryById(@PathVariable("id") Long id) {
        Country foundCountry = countryService.getCountryById(id);
        CountryDTO foundCountryDTO = genericMapper.mapType(foundCountry, CountryDTO.class);
        ResponseEntity<CountryDTO> responseEntity = ResponseEntity.ok(foundCountryDTO);
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<CountryDTO> createCountry(@RequestBody CountryCreateDTO countryCreateDTO) {
        Country Country = genericMapper.mapType(countryCreateDTO, Country.class);
        Country createdCountry = countryService.createCountry(Country);
        CountryDTO createdCountryDTO = genericMapper.mapType(createdCountry, CountryDTO.class);
        ResponseEntity<CountryDTO> responseEntity = ResponseEntity.ok(createdCountryDTO);
        return responseEntity;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CountryDTO> updateCountry(@PathVariable("id") Long id,
            @RequestBody CountryDTO details) {
        details.setId(id);
        Country country = genericMapper.mapTypeForPatch(details, Country.class);
        Country updatedCountry = countryService.updateCountry(country);

        CountryDTO updatedCountryDTO = genericMapper.mapType(updatedCountry, CountryDTO.class);
        ResponseEntity<CountryDTO> responseEntity = ResponseEntity.ok(updatedCountryDTO);
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCountryById(@PathVariable("id") Long id) {
        countryService.deleteCountryById(id);
        ResponseEntity<String> responseEntity = ResponseEntity.ok("Country deleted");
        return responseEntity;
    }

}
