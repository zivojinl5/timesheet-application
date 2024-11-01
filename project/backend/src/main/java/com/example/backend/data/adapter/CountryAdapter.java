package com.example.backend.data.adapter;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.example.backend.data.entity.CountryEntity;
import com.example.backend.data.repository.ICountryJPARepository;
import com.example.backend.domain.domain_repository.ICountryDomainRepository;
import com.example.backend.domain.model.model.Country;
import com.example.backend.mapper.GenericMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class CountryAdapter implements ICountryDomainRepository {

    private final ICountryJPARepository countryJPARepository;
    private final GenericMapper genericMapper;

    @Override
    public Page<Country> findAll(PageRequest pageRequest) {
        Page<CountryEntity> countryEntityPage = countryJPARepository.findAll(pageRequest);
        return genericMapper.mapPage(countryEntityPage, Country.class);
    }

    @Override
    public List<Country> findAll() {
        List<CountryEntity> countryEntities = countryJPARepository.findAll();
        List<Country> countries = genericMapper.mapList(countryEntities, Country.class);
        return countries;
    }

    @Override
    public Country findById(Long id) {
        CountryEntity countryEntity = countryJPARepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Country not found"));
        Country country = genericMapper.mapType(countryEntity, Country.class);
        return country;
    }

    @Override
    public Country save(Country country) {
        CountryEntity countryEntity = genericMapper.mapType(country, CountryEntity.class);
        CountryEntity createdCountryEntity = countryJPARepository.save(countryEntity);
        Country createdCountry = genericMapper.mapType(createdCountryEntity, Country.class);
        return createdCountry;
    }

    @Override
    public Country update(Country country) {
        CountryEntity countryEntity = genericMapper.mapType(country, CountryEntity.class);
        CountryEntity updatedEntity = countryJPARepository.save(countryEntity);
        Country updatedCountry = genericMapper.mapTypeForPatch(updatedEntity, Country.class);
        return updatedCountry;
    }

    @Override
    public void deleteById(Long id) {
        countryJPARepository.deleteById(id);
    }

}
