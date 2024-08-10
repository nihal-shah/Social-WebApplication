package com.example.webapplication.controler;

import com.example.webapplication.model.Client;
import com.example.webapplication.jpaclientrepository.JpaClientRepository;
import com.example.webapplication.service.ClientServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class RestControler {

    @Autowired
    ClientServiceImplementation Csi;

    @Autowired
    JpaClientRepository Jcr;
    @GetMapping("/")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/get-all-user")
    public List<Client> getAllClietDetails(){
        List<Client> Clients = Jcr.findAll();
        for(Client client:Clients){
            System.out.println(client.getFollower());
            System.out.println(client.getFollowing());
        }
        return Clients;
    }

    @GetMapping("/get-user-id/{id}")
    public Client getClientDetailsById(@PathVariable int id) throws Exception{
        return Csi.getClientById(id);
    }

    @GetMapping("/get-user-email/{email}")
    public Client getClientDetailsByEmail(@PathVariable String email) throws Exception{
        return Csi.getClientByEmail(email);
    }

    @PostMapping("/create-new-client")
    public String CreateClient(@RequestBody Client clientDetails){
        return Csi.registerClient(clientDetails);
    }

    @PutMapping("/update-client-details/{id}")
    public String UpdateClientDetails(@RequestBody Client clientDetails,@PathVariable int id) throws Exception{
        return Csi.updateUserDetails(clientDetails,id);
    }

//    @DeleteMapping("/delete-client/{id}")
//    public String DeleteClient(@PathVariable int id) throws Exception{
//        Optional<Client> clientOptional=Jcr.findById(id);
//        if(clientOptional.isPresent()){
//            Client client=clientOptional.get();
//            Jcr.delete(client);
//            return "Client "+client.getClientname()+" deleted successfully";
//        }
//        else{
//            throw new Exception("Client with id "+id+" not found");
//        }
//
//    }

    @GetMapping("/search")
    public List<Client> searchClient(@RequestParam("name") String name){
        List<Client> clientList=Csi.searchUser(name);
        return clientList;
    }

    @PutMapping("/follow/{requestTo}/{requestBy}")
    public String followClient(@PathVariable Integer requestBy,@PathVariable Integer requestTo) throws Exception{
       return Csi.followUser(requestTo,requestBy);
    }

    @PutMapping("/unfollow/{requestTo}/{requestBy}")
    public String unFollowClient(@PathVariable Integer requestBy,@PathVariable Integer requestTo) throws Exception{
        return Csi.unfollowUser(requestTo,requestBy);
    }
}
