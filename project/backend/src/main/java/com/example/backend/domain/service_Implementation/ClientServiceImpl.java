package com.example.backend.domain.service_Implementation;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.backend.domain.domain_repository.IClientDomainRepository;
import com.example.backend.domain.model.create_model.ClientCreateModel;
import com.example.backend.domain.model.model.Client;
import com.example.backend.domain.model.search_model.ClientSearchModel;
import com.example.backend.domain.model.update_model.ClientUpdateModel;
import com.example.backend.domain.service.IClientService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ClientServiceImpl implements IClientService {

    private final IClientDomainRepository clientDomainRepository;

    @Override
    public List<Client> getAllClients() {
        return clientDomainRepository.findAll();
    }

    @Override
    public Page<Client> getAllClientsWithPaginationAndSorting(PageRequest pageRequest) {
        return clientDomainRepository.findAll(pageRequest);
    }

    @Override
    public Client getClientById(Long id) {
        return clientDomainRepository.findById(id);
    }

    @Override
    public HashMap<Long, String> getClientNamesByUserId(Long id) {
        return clientDomainRepository.findClientNamesByUserId(id);

    }

    @Override
    public Client createClient(ClientCreateModel clientCreateModel) {
        return clientDomainRepository.save(clientCreateModel);
    }

    @Override
    public Client updateClient(ClientUpdateModel clientUpdateModel) {
        return clientDomainRepository.update(clientUpdateModel);
    }

    @Override
    public void deleteClientById(Long id) {
        clientDomainRepository.deleteById(id);
    }

    @Override
    public Page<Client> searchClients(ClientSearchModel clientSearchModel) {
        return clientDomainRepository.searchClients(clientSearchModel);

    }

    @Override
    public List<String> findDistinctStartingLetters() {
        return clientDomainRepository.findDistinctStartingLetters();
    }

}