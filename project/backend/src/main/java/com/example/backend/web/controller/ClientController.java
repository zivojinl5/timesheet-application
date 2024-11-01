package com.example.backend.web.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.domain.model.create_model.ClientCreateModel;
import com.example.backend.domain.model.model.Client;
import com.example.backend.domain.model.search_model.ClientSearchModel;
import com.example.backend.domain.model.update_model.ClientUpdateModel;
import com.example.backend.domain.service.IClientService;
import com.example.backend.mapper.GenericMapper;
import com.example.backend.web.dto.create_dto.ClientCreateDTO;
import com.example.backend.web.dto.dto.ClientDTO;
import com.example.backend.web.dto.update_dto.ClientUpdateDTO;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/clients")
@CrossOrigin
public class ClientController {
    private final IClientService clientService;
    private final GenericMapper genericMapper;

    @GetMapping("/startingLetters")
    public List<String> getStartingLetters() {
        return clientService.findDistinctStartingLetters();
    }

    @GetMapping("/search/{startingLetter}/{pageNumber}/{pageSize}/{sortingProperty}/{sortingDirection}")
    public ResponseEntity<Page<ClientDTO>> searchClients(@PathVariable String startingLetter,
            @PathVariable int pageNumber, @PathVariable int pageSize, @PathVariable String sortingProperty,
            @PathVariable String sortingDirection, @RequestParam(required = false) String searchString) {

        Direction direction = sortingDirection.equals("ASC") ? Direction.ASC : Direction.DESC;
        Sort sort = Sort.by(direction, sortingProperty);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        ClientSearchModel clientSearchModel = new ClientSearchModel();
        clientSearchModel.setPageRequest(pageRequest);
        clientSearchModel.setStartingLetter(startingLetter);
        clientSearchModel.setSearchString(searchString);

        Page<Client> clientPage = clientService.searchClients(clientSearchModel);
        Page<ClientDTO> clientDTOPage = genericMapper.mapPage(clientPage, ClientDTO.class);
        ResponseEntity<Page<ClientDTO>> responseEntity = ResponseEntity.ok(clientDTOPage);
        return responseEntity;
    }

    @GetMapping("/pagination/{pageNumber}/{pageSize}/{sortingProperty}/{sortingDirection}")
    public ResponseEntity<Page<ClientDTO>> getAllClientsWithPaginationAndSorting(@PathVariable int pageNumber,
            @PathVariable int pageSize, @PathVariable String sortingProperty, @PathVariable String sortingDirection) {
        Direction direction = sortingDirection.equals("ASC") ? Direction.ASC : Direction.DESC;
        Sort sort = Sort.by(direction, sortingProperty);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        Page<Client> clientPage = clientService.getAllClientsWithPaginationAndSorting(pageRequest);
        Page<ClientDTO> clientDTOPage = genericMapper.mapPage(clientPage, ClientDTO.class);
        ResponseEntity<Page<ClientDTO>> responseEntity = ResponseEntity.ok(clientDTOPage);
        return responseEntity;
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        List<ClientDTO> clientDTOs = genericMapper.mapList(clients, ClientDTO.class);
        ResponseEntity<List<ClientDTO>> responseEntity = ResponseEntity.ok(clientDTOs);
        return responseEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable("id") Long id) {
        Client foundClient = clientService.getClientById(id);
        ClientDTO foundClientDTO = genericMapper.mapType(foundClient, ClientDTO.class);
        ResponseEntity<ClientDTO> responseEntity = ResponseEntity.ok(foundClientDTO);
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientCreateDTO clientCreateDTO) {
        ClientCreateModel clientCreateModel = genericMapper.mapType(clientCreateDTO, ClientCreateModel.class);
        Client createdClient = clientService.createClient(clientCreateModel);
        ClientDTO createdClientDTO = genericMapper.mapType(createdClient, ClientDTO.class);
        ResponseEntity<ClientDTO> responseEntity = ResponseEntity.ok(createdClientDTO);
        return responseEntity;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable("id") Long id,
            @RequestBody ClientUpdateDTO details) {
        details.setId(id);
        ClientUpdateModel clientUpdateModel = genericMapper.mapTypeForPatch(details, ClientUpdateModel.class);
        Client updatedClient = clientService.updateClient(clientUpdateModel);

        ClientDTO updatedClientDTO = genericMapper.mapType(updatedClient, ClientDTO.class);
        ResponseEntity<ClientDTO> responseEntity = ResponseEntity.ok(updatedClientDTO);
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClientById(@PathVariable("id") Long id) {
        clientService.deleteClientById(id);
        ResponseEntity<String> responseEntity = ResponseEntity.ok("Client deleted");
        return responseEntity;
    }

}