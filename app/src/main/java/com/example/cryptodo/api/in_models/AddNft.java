package com.example.cryptodo.api.in_models;

public class AddNft {
    public String owner;
    public long totalSupply;
    public String name;
    public String title;
    public String symbol;
    public int token_per_tx;
    public int token_per_wallet;
    public float start_price;
    public String time_for_grown;
    public String founder;
    public String uri;
    public boolean increment_max_amount;
    public boolean presale;
    public String node;
    public boolean test_mode;

    public AddNft(String owner, long totalSupply, String name, String title, String symbol,
                  int tokenPerTx, int tokenPerWallet, float startPrice, String timeForGrown,
                  String founder, String uri, boolean incrementMaxAmount, boolean presale,
                  String node) {
        this.owner = owner;
        this.totalSupply = totalSupply;
        this.name = name;
        this.title = title;
        this.symbol = symbol;
        this.token_per_tx = tokenPerTx;
        this.token_per_wallet = tokenPerWallet;
        this.start_price = startPrice;
        this.time_for_grown = timeForGrown;
        this.founder = founder;
        this.uri = uri;
        this.increment_max_amount = incrementMaxAmount;
        this.presale = presale;
        this.node = node;
        this.test_mode = true;

    }

}
