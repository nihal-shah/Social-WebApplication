package com.example.webapplication.controler;

import com.example.webapplication.databaseschema.Client;
import com.example.webapplication.jpaclientrepository.JpaClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class RestControler {

    @Autowired
    JpaClientRepository Jcr;

    @GetMapping("/")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/get-all-user")
    public List<Client> getAllClietDetails(){
        List<Client> Clients = Jcr.findAll();
        return Clients;
    }

    @GetMapping("/get-user/{id}")
    public Client getClientDetailsById(@PathVariable int id) throws Exception{
        Optional<Client> clientOptional=Jcr.findById(id);
        if(clientOptional.isPresent()){
            Client client=clientOptional.get();
            return client;
        }
        else{
            throw new Exception("Client with id "+id+" not found");
        }

    }

    @PostMapping("/create-new-client")
    public String CreateClient(@RequestBody Client clientDetails){
        Client client=new Client();
        client.setClientid(clientDetails.getClientid());
        client.setClientname(clientDetails.getClientname());
        client.setClientemail(clientDetails.getClientemail());
        client.setClientpassword(clientDetails.getClientpassword());
        Jcr.save(client);
        return "Client created "+client.getClientname()+" successfully";
    }

    @PutMapping("/update-client-details/{id}")
    public String UpdateClientDetails(@RequestBody Client clientDetails,@PathVariable int id) throws Exception{
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

    @DeleteMapping("/delete-client/{id}")
    public String DeleteClient(@PathVariable int id) throws Exception{
        Optional<Client> clientOptional=Jcr.findById(id);
        if(clientOptional.isPresent()){
            Client client=clientOptional.get();
            Jcr.delete(client);
            return "Client "+client.getClientname()+" deleted successfully";
        }
        else{
            throw new Exception("Client with id "+id+" not found");
        }

    }
}
