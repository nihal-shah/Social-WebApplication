package com.example.webapplication.jpaclientrepository;

import com.example.webapplication.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface JpaClientRepository extends JpaRepository<Client,Integer> {
    public Optional<Client> findByClientemail(String email);

    @Query("SELECT c FROM Client c where c.clientname LIKE %:name%")
//@Query("SELECT c FROM Client c where c.clientname LIKE CONCAT('%', :name, '%')")
    public List<Client> searchUser(@Param("name") String name);
}
