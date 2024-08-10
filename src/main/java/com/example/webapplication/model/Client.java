package com.example.webapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Client {
    @Id
    private Integer clientid;
    private String clientname;
    private String clientemail;
    private String clientpassword;
    private Set<Integer> follower= new HashSet<Integer>();
    private Set<Integer> following=new HashSet<Integer>();

    public Client() {

    }

    public Client(Integer clientid, String clientname, String clientemail, String clientpassword) {
        this.clientid = clientid;
        this.clientname = clientname;
        this.clientemail = clientemail;
        this.clientpassword = clientpassword;

    }

    public Set<Integer> getFollower() {
        return follower;
    }

    public void setFollower(Set<Integer> follower) {
        this.follower = follower;
    }

    public Integer getClientid() {
        return clientid;
    }

    public void setClientid(Integer clientid) {
        this.clientid = clientid;
    }

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    public String getClientemail() {
        return clientemail;
    }

    public void setClientemail(String clientemail) {
        this.clientemail = clientemail;
    }

    public String getClientpassword() {
        return clientpassword;
    }

    public void setClientpassword(String clientpassword) {
        this.clientpassword = clientpassword;
    }

    public Set<Integer> getFollowing() {
        return following;
    }

    public void setFollowing(Set<Integer> following) {
        this.following = following;
    }
}