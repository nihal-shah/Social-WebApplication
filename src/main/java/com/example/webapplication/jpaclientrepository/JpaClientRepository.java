package com.example.webapplication.jpaclientrepository;

import com.example.webapplication.databaseschema.Client;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JpaClientRepository extends JpaRepository<Client,Integer> {
}
