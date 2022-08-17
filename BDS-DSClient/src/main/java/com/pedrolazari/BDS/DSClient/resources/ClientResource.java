package com.pedrolazari.BDS.DSClient.resources;

import com.pedrolazari.BDS.DSClient.Dto.ClientDto;
import com.pedrolazari.BDS.DSClient.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<Page<ClientDto>> findAllPaged(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "5") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction)
    {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<ClientDto> clientDtos = clientService.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(clientDtos);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable Long id){
        ClientDto clientDto = clientService.findById(id);
        return ResponseEntity.ok().body(clientDto);
    }

    @PostMapping
    public ResponseEntity<ClientDto> insertClient(@RequestBody ClientDto clientDto){
        clientDto = clientService.insertClient(clientDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clientDto.getId()).toUri();
        return ResponseEntity.created(uri).body(clientDto);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable Long id, @RequestBody ClientDto clientDto){
        clientDto = clientService.updateClient(id, clientDto);
        return ResponseEntity.ok().body(clientDto);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
