package com.example.backend.domain.service_Implementation;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.backend.domain.domain_repository.ICountryDomainRepository;
import com.example.backend.domain.model.model.Country;
import com.example.backend.domain.service.ICountryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CountryServiceImpl implements ICountryService {

    private final ICountryDomainRepository countryDomainRepository;

    @Override
    public List<Country> getAllCountries() {
        return countryDomainRepository.findAll();
    }

    @Override
    public Page<Country> getAllCountriesWithPaginationAndSorting(PageRequest pageRequest) {
        return countryDomainRepository.findAll(pageRequest);
    }

    @Override
    public Country getCountryById(Long id) {
        return countryDomainRepository.findById(id);
    }

    @Override
    public Country createCountry(Country country) {
        return countryDomainRepository.save(country);
    }

    @Override
    public Country updateCountry(Country country) {
        return countryDomainRepository.update(country);
    }

    @Override
    public void deleteCountryById(Long id) {
        countryDomainRepository.deleteById(id);
    }
}