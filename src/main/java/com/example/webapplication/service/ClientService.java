package com.example.webapplication.service;

import com.example.webapplication.model.Client;

import java.util.List;

public interface ClientService {
    public String registerClient(Client client);
    public Client getClientById(Integer clientId) throws Exception;
    public Client getClientByEmail(String email) throws Exception;
    public String followUser(Integer clientId, Integer followerId);
    public String unfollowUser(Integer clientId, Integer followerId);
    public List<Client> searchUser(String username);
    public String updateUserDetails(Client client,Integer id) throws Exception;
}
