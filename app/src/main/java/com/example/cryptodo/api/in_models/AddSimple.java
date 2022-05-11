package com.example.cryptodo.api.in_models;

public class AddSimple {
    public String owner;
    public int totalSupply;
    public int decimals;
    public String name;
    public String title;
    public String symbol;
    public String node = "bsc";
    public boolean testMode = false;
    public boolean burn = false;
    public boolean mint = false;
    public int feePercent = 0;
    public String feeAddress = "";
    public int taxBurningPercent = 0;
    public int stakingPercent = 0;
    public boolean forkSafemoon = false;
}
