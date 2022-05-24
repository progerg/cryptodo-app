package com.example.cryptodo.api;

import com.example.cryptodo.api.in_models.NFTContract;
import com.example.cryptodo.api.in_models.SimpleContract;
import com.example.cryptodo.api.in_models.User;
import com.example.cryptodo.api.in_models.ContractStatus;
import com.example.cryptodo.api.out_models.UserOut;
import com.example.cryptodo.api.out_models.ContractOut;
import com.example.cryptodo.api.out_models.ContractStatusOut;
import com.example.cryptodo.db.DB;

import java.io.IOException;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ThreadAddContract extends Thread {
    private String id;
    private SimpleContract contract;
    private NFTContract contractNFT;
    private DB mDBConnector;
    String contractType;

    public ThreadAddContract(String id, SimpleContract contract, DB mDBConnector) {
        this.id = id;
        this.contract = contract;
        this.mDBConnector = mDBConnector;
    }

    public ThreadAddContract(String id, NFTContract contract, DB mDBConnector) {
        this.id = id;
        this.contractNFT = contract;
        this.mDBConnector = mDBConnector;
    }

    @Override
    public void run() {
        String baseUrl = "https://api.cryptodo.app/api/";
        UserOut userOut = new UserOut();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserService service = retrofit.create(UserService.class);
        Call<UserOut> call = service.loginMobile(new User(id));
        try {
            userOut = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!userOut.token.isEmpty()) {
            TokenInterceptor interceptor=new TokenInterceptor(userOut.token);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();
            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Call<ContractOut> callContract;
            ContractService contractService = retrofit.create(ContractService.class);
            if (contract.isEmpty()) {
                callContract = contractService.addNft(contractNFT);
                contractType = "erc721";
            } else {
                callContract = contractService.add(contract);
                contractType = "erc20";
            }
            try {
                ContractOut contractOut = callContract.execute().body();
                ContractStatusOut status = new ContractStatusOut();

                Call<ContractStatusOut> callStatus;
                for (int i = 0; i < 7; i++) {
                    try {
                        callStatus = contractService.status(new ContractStatus(contractOut.checkCode));
                        status = callStatus.execute().body();
                    } catch (Exception e) {}
                    Thread.sleep(90000);
                }

                if (!status.isEmpty()) {
                    mDBConnector.insertStatus("ok", status.url, contractType);
                } else {
                    mDBConnector.insertStatus("error", "", contractType);
                }

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}

