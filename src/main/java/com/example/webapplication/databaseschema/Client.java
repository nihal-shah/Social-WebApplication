package com.example.webapplication.databaseschema;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Client {
    @Id
    private Integer clientid;
    @Column
    private String clientname;
    @Column
    private String clientemail;
    @Column
    private String clientpassword;

    public Client() {

    }

    public Client(Integer clientid, String clientname, String clientemail, String clientpassword) {
        this.clientid = clientid;
        this.clientname = clientname;
        this.clientemail = clientemail;
        this.clientpassword = clientpassword;
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
}