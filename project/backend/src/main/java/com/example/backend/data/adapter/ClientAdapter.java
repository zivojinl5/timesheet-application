package com.example.backend.data.adapter;

import java.util.HashMap;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.example.backend.data.entity.ClientEntity;
import com.example.backend.data.entity.CountryEntity;
import com.example.backend.data.repository.IClientJPARepository;
import com.example.backend.data.repository.ICountryJPARepository;
import com.example.backend.domain.domain_repository.IClientDomainRepository;
import com.example.backend.domain.model.create_model.ClientCreateModel;
import com.example.backend.domain.model.model.Client;
import com.example.backend.domain.model.search_model.ClientSearchModel;
import com.example.backend.domain.model.update_model.ClientUpdateModel;
import com.example.backend.mapper.GenericMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class ClientAdapter implements IClientDomainRepository {
    private final IClientJPARepository clientJPARepository;
    private final ICountryJPARepository countryJPARepository;
    private final GenericMapper genericMapper;

    @Override
    public HashMap<Long, String> findClientNamesByUserId(Long id) {
        return clientJPARepository.findClientNamesByUserId(id);

    }

    @Override
    public List<String> findDistinctStartingLetters() {
        return clientJPARepository.findDistinctStartingLetters();

    }

    @Override
    public Page<Client> searchClients(ClientSearchModel clientSearchModel) {
        PageRequest pageRequest = clientSearchModel.getPageRequest();
        Page<ClientEntity> clientEntityPage = clientJPARepository.searchClients(clientSearchModel, pageRequest);
        Page<Client> clientPage = genericMapper.mapPage(clientEntityPage, Client.class);
        return clientPage;
    }

    @Override
    public Page<Client> findAll(PageRequest pageRequest) {
        Page<ClientEntity> clientEntityPage = clientJPARepository.findAll(pageRequest);
        Page<Client> clientPage = genericMapper.mapPage(clientEntityPage, Client.class);
        return clientPage;
    }

    @Override
    public List<Client> findAll() {
        List<ClientEntity> clientEntities = clientJPARepository.findAll();
        List<Client> clients = genericMapper.mapList(clientEntities, Client.class);
        return clients;
    }

    @Override
    public Client findById(Long id) {
        ClientEntity clientEntity = clientJPARepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));
        Client client = genericMapper.mapType(clientEntity, Client.class);
        return client;
    }

    @Override
    public Client save(ClientCreateModel clientCreateModel) {
        ClientEntity clientEntity = genericMapper.mapType(clientCreateModel, ClientEntity.class);
        CountryEntity countryEntity = countryJPARepository.findById(clientCreateModel.getCountryId())
                .orElseThrow(() -> new IllegalArgumentException("Country not found"));
        clientEntity.setCountry(countryEntity);
        ClientEntity createdClientEntity = clientJPARepository.save(clientEntity);
        Client createdClient = genericMapper.mapType(createdClientEntity, Client.class);
        return createdClient;
    }

    @Override
    public Client update(ClientUpdateModel clientUpdateModel) {
        // Check if the provided client ID is valid
        ClientEntity existingClientEntity = clientJPARepository.findById(clientUpdateModel.getId())
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));

        // Map the fields from the update model to the entity
        existingClientEntity = genericMapper.mapTypeForPatch(clientUpdateModel, ClientEntity.class);

        // Check if a new countryId is provided in the update request
        if (clientUpdateModel.getCountryId() != null) {
            // Retrieve the country entity using the provided ID
            CountryEntity countryEntity = countryJPARepository.findById(clientUpdateModel.getCountryId())
                    .orElseThrow(() -> new IllegalArgumentException("Country not found"));
            // Set the country for the client entity
            existingClientEntity.setCountry(countryEntity);
        }

        // Save the updated client entity
        ClientEntity updatedEntity = clientJPARepository.save(existingClientEntity);

        // Map the updated entity back to the Client DTO
        Client updatedClient = genericMapper.mapType(updatedEntity, Client.class);
        return updatedClient;
    }

    @Override
    public void deleteById(Long id) {
        clientJPARepository.deleteById(id);
    }

}
