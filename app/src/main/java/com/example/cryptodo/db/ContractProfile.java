package com.example.cryptodo.db;

public class ContractProfile {
    public String title;
    public String type;
    public String url;
    public String status;
    public String blockchain;


    public ContractProfile(String title, String type, String url, String status, String blockchain) {
        this.title = title;
        this.type = type;
        this.url = url;
        this.status = status;
        this.blockchain = blockchain;
    }
}

