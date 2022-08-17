package com.pedrolazari.BDS.DSClient.services;

import com.pedrolazari.BDS.DSClient.Dto.ClientDto;
import com.pedrolazari.BDS.DSClient.entities.Client;
import com.pedrolazari.BDS.DSClient.repositories.ClientRepository;
import com.pedrolazari.BDS.DSClient.resources.ClientResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Page<ClientDto> findAllPaged(PageRequest pageRequest) {
        Page<Client> clients = clientRepository.findAll(pageRequest);
        return clients.map( client -> new ClientDto(client));
    }
}
