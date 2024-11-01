package com.example.backend.domain.domain_repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.backend.domain.model.create_model.ClientCreateModel;
import com.example.backend.domain.model.model.Client;
import com.example.backend.domain.model.search_model.ClientSearchModel;
import com.example.backend.domain.model.update_model.ClientUpdateModel;

public interface IClientDomainRepository {

    Page<Client> findAll(PageRequest pageRequest);

    List<Client> findAll();

    Client findById(Long id);

    HashMap<Long, String> findClientNamesByUserId(Long userId);

    Client save(ClientCreateModel clientCreateModel);

    Client update(ClientUpdateModel client);

    void deleteById(Long id);

    Page<Client> searchClients(ClientSearchModel clientSearchModel);

    List<String> findDistinctStartingLetters();

}