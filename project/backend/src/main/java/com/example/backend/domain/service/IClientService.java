package com.example.backend.domain.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.backend.domain.model.create_model.ClientCreateModel;
import com.example.backend.domain.model.model.Client;
import com.example.backend.domain.model.search_model.ClientSearchModel;
import com.example.backend.domain.model.update_model.ClientUpdateModel;

public interface IClientService {

    List<Client> getAllClients();

    Page<Client> getAllClientsWithPaginationAndSorting(PageRequest pageRequest);

    Client getClientById(Long id);

    Client createClient(ClientCreateModel clientCreateModel);

    Client updateClient(ClientUpdateModel model);

    void deleteClientById(Long id);

    Page<Client> searchClients(ClientSearchModel clientSearchModel);

    HashMap<Long, String> getClientNamesByUserId(Long id);

    List<String> findDistinctStartingLetters();

}