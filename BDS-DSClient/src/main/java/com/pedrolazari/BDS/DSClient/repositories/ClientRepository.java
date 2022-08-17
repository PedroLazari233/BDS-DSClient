package com.pedrolazari.BDS.DSClient.repositories;

import com.pedrolazari.BDS.DSClient.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
