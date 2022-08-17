package com.pedrolazari.BDS.DSClient.Dto;

import com.pedrolazari.BDS.DSClient.entities.Client;
import lombok.Data;

import javax.persistence.Column;
import java.time.Instant;

@Data
public class ClientDto {
    private Long id;
    private String name;
    private String cpf;
    private Double income;
    private Instant birthDate;
    private Integer children;

    public ClientDto(Client client){
        this.id = client.getId();
        this.name = client.getName();
        this.cpf = client.getCpf();
        this.income = client.getIncome();
        this.birthDate = client.getBirthDate();
        this.children = client.getChildren();
    }
}
