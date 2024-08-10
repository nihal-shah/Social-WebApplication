package com.example.webapplication.service;

import com.example.webapplication.model.Client;
import com.example.webapplication.jpaclientrepository.JpaClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ClientServiceImplementation implements ClientService {

    @Autowired
    JpaClientRepository Jcr;

    @Override
    public String registerClient(Client clientDetails) {
        Client client=new Client();
        client.setClientid(clientDetails.getClientid());
        client.setClientname(clientDetails.getClientname());
        client.setClientemail(clientDetails.getClientemail());
        client.setClientpassword(clientDetails.getClientpassword());
        Jcr.save(client);
        return "Client created "+client.getClientname()+" successfully";
    }

    @Override
    public Client getClientById(Integer clientId) throws Exception {
        Optional<Client> clientOptional=Jcr.findById(clientId);
        if(clientOptional.isPresent()){
            Client client=clientOptional.get();
            return client;
        }
        else{
            throw new Exception("Client with id "+clientId+" not found");
        }
    }

    @Override
    public Client getClientByEmail(String clientemail) throws Exception {
        Optional<Client> clientOptional= Jcr.findByClientemail(clientemail);
        if(clientOptional.isPresent()){
            Client client=clientOptional.get();
            return client;
        }
        else{
            throw new Exception("Client with id "+clientemail+" not found");
        }
    }

    @Override
    public String followUser(Integer clientId, Integer followerId) {
        Optional<Client> clientOptional=Jcr.findById(clientId);
        Optional<Client> clientFollowerOptional=Jcr.findById(followerId);
        if(clientOptional.isPresent() && clientFollowerOptional.isPresent()){
            Client client=clientOptional.get();
            Client clientFollower=clientFollowerOptional.get();
            Set<Integer> requestTo=client.getFollower();
            Set<Integer> requestedBy=clientFollower.getFollowing();
            requestTo.add(followerId);
            requestedBy.add(clientId);
            client.setFollower(requestTo);
            clientFollower.setFollowing(requestedBy);
            Jcr.save(client);
            Jcr.save(clientFollower);
            return "Clinet with id "+clientId+" followed by id "+followerId+" successfully";
        }
        return "client with id"+clientId+"not found";
    }

    @Override
    public String unfollowUser(Integer clientId, Integer followerId) {
        Optional<Client> clientOptional=Jcr.findById(clientId);
        Optional<Client> clientFollowerOptional=Jcr.findById(followerId);
        if(clientOptional.isPresent() && clientFollowerOptional.isPresent()){
            Client client=clientOptional.get();
            Client clientFollower=clientFollowerOptional.get();
            Set<Integer> requestTo=client.getFollower();
            Set<Integer> requestedBy=clientFollower.getFollowing();
            requestTo.remove(followerId);
            requestedBy.remove(clientId);
            client.setFollower(requestTo);
            clientFollower.setFollowing(requestedBy);
            Jcr.save(client);
            Jcr.save(clientFollower);
            return "Clinet with id "+clientId+" unfollowed by id "+followerId+" successfully";
        }

        return "client with id"+clientId+"not found";
    }

    @Override
    public List<Client> searchUser(String name) {

        return Jcr.searchUser(name);
    }

    @Override
    public String updateUserDetails(Client clientDetails,Integer id) throws Exception {
        Optional<Client> clientOptional=Jcr.findById(id);
        if(clientOptional.isPresent()){
            Client client=clientOptional.get();
            if(clientDetails.getClientid()!=null && clientDetails.getClientid()>0){
                client.setClientid(clientDetails.getClientid());
            }
            if(clientDetails.getClientname()!=null){
                client.setClientname(clientDetails.getClientname());
            }
            if(clientDetails.getClientemail()!=null){
                client.setClientemail(clientDetails.getClientemail());
            }
            if(clientDetails.getClientpassword()!=null){
                client.setClientpassword(clientDetails.getClientpassword());
            }
            Jcr.save(client);
            return "Client "+client.getClientname()+" updated successfully";
        }
        else{
            throw new Exception("Client with id "+id+" not found");
        }
    }
}
