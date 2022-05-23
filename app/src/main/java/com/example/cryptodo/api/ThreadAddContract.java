package com.example.cryptodo.api;

import android.os.AsyncTask;

import com.example.cryptodo.api.in_models.AddNft;
import com.example.cryptodo.api.in_models.AddSimple;
import com.example.cryptodo.api.in_models.AddUser;
import com.example.cryptodo.api.in_models.ContractStatus;
import com.example.cryptodo.api.out_models.AddUserOut;
import com.example.cryptodo.api.out_models.ContractAddOut;
import com.example.cryptodo.api.out_models.ContractStatusOut;
import com.example.cryptodo.db.DB;

import java.io.IOException;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ThreadAddContract extends Thread {
    private String id;
    private String type;
    private AddSimple contract;
    private AddNft contractNFT;
    private DB mDBConnector;

    public ThreadAddContract(String id, AddSimple contract, DB mDBConnector) {
        this.id = id;
        this.contract = contract;
        this.mDBConnector = mDBConnector;
    }

    public ThreadAddContract(String id, AddNft contract, DB mDBConnector) {
        this.id = id;
        this.contractNFT = contract;
        this.mDBConnector = mDBConnector;
    }

    @Override
    public void run() {
        String baseUrl = "https://api.cryptodo.app/api/";
        AddUserOut userOut = new AddUserOut();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserService service = retrofit.create(UserService.class);
        Call<AddUserOut> call = service.loginMobile(new AddUser(id));
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

            Call<ContractAddOut> callContract;
            ContractService contractService = retrofit.create(ContractService.class);
            if (contract.isEmpty()) {
                callContract = contractService.addNft(contractNFT);
            } else {
                callContract = contractService.add(contract);
            }
            try {
                ContractAddOut contractAddOut = callContract.execute().body();

                Call<ContractStatusOut> callStatus;
                for (int i = 0; i < 7; i++) {
                    try {
                        callStatus = contractService.status(new ContractStatus(contractAddOut.checkCode));
                        ContractStatusOut status = callStatus.execute().body();
                    } catch (Exception e) {}
                    Thread.sleep(90000);
                }

                // TODO: добавить тут запрос в базу на добавление

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}

