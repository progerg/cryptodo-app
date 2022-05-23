package com.example.cryptodo.api.in_models;

public class AddSimple {
    public String owner;
    public long totalSupply;
    public int decimals;
    public String name;
    public String title;
    public String symbol;
    public String node;
    public boolean test_mode;
    public boolean burn;
    public boolean mint;
    //  public int feePercent;
    //  public String feeAddress;
    //  public int taxBurningPercent;
    //  public int stakingPercent;
    public boolean fork_safemoon;

    public AddSimple(String owner, long totalSupply, int decimals, String name,
                     String title, String symbol, String node, boolean burn, boolean mint,
                     boolean forkSafemoon) {
        this.owner = owner;
        this.totalSupply = totalSupply;
        this.name = name;
        this.title = title;
        this.symbol = symbol;
        this.node = node;
        this.test_mode = true;
        this.decimals = decimals;
        this.burn = burn;
        this.mint = mint;
        this.fork_safemoon = forkSafemoon;
    }
}
