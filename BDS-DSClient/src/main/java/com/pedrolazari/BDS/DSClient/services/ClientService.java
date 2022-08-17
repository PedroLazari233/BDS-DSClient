package com.pedrolazari.BDS.DSClient.services;

import com.pedrolazari.BDS.DSClient.Dto.ClientDto;
import com.pedrolazari.BDS.DSClient.entities.Client;
import com.pedrolazari.BDS.DSClient.repositories.ClientRepository;
import com.pedrolazari.BDS.DSClient.services.exceptions.DataBaseException;
import com.pedrolazari.BDS.DSClient.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Page<ClientDto> findAllPaged(PageRequest pageRequest) {
        Page<Client> clients = clientRepository.findAll(pageRequest);
        return clients.map( client -> new ClientDto(client));
    }

    @Transactional(readOnly = true)
    public ClientDto findById(Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        Client client = clientOptional.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new ClientDto(client);
    }

    @Transactional
    public ClientDto insertClient(ClientDto clientDto) {
        Client client = new Client();
        copyDtoToEntity(clientDto, client);
        client = clientRepository.save(client);
        return new ClientDto(client);
    }

    @Transactional
    public ClientDto updateClient(Long id, ClientDto clientDto) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        Client client = clientOptional.orElseThrow(() -> new ResourceNotFoundException("Id not found " + id));
        copyDtoToEntity(clientDto, client);
        client = clientRepository.save(client);
        return new ClientDto(client);
    }

    public void deleteClient(Long id) {
        try{
            clientRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e)
        {
            throw  new ResourceNotFoundException("Id not found " + id);
        }
        catch (DataIntegrityViolationException e)
        {
            throw  new DataBaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(ClientDto clientDto, Client client){
        client.setName(clientDto.getName());
        client.setCpf(clientDto.getCpf());
        client.setIncome(client.getIncome());
        client.setBirthDate(clientDto.getBirthDate());
        client.setChildren(clientDto.getChildren());
    }
}
