package com.pedrolazari.BDS.DSClient.entities;

import com.pedrolazari.BDS.DSClient.Dto.ClientDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Table(name = "tb_client")
@NoArgsConstructor
public class Client {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cpf;
    private Double income;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant birthDate;
    private Integer children;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant UpdateAt;

    @PrePersist
    public void prePersist(){
        createdAt = Instant.now();
    }
    @PreUpdate
    public void preUpdate(){
        UpdateAt = Instant.now();
    }
}
