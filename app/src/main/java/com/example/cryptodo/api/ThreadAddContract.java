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
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ThreadAddContract extends Thread {
    private String id;
    private SimpleContract contract;
    private NFTContract contractNFT;
    private DB mDBConnector;
    String contractType;
    String baseUrl = "https://api.cryptodo.app/api/";

    public ThreadAddContract(String id, SimpleContract contract, DB mDBConnector) {
        this.id = id;
        this.contract = contract;
        this.mDBConnector = mDBConnector;
        this.contractType = "erc20";
    }

    public ThreadAddContract(String id, NFTContract contract, DB mDBConnector) {
        this.id = id;
        this.contractNFT = contract;
        this.mDBConnector = mDBConnector;
        this.contractType = "erc721";
    }

    public OkHttpClient getContractClientJWT(String token) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        TokenInterceptor interceptor = new TokenInterceptor(token);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(logging)
                .build();
        return client;
    }

    public ContractService getContractServiceJWT(String token) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getContractClientJWT(token))
                .build();
        ContractService service = retrofit.create(ContractService.class);
        return service;
    }

    public ContractService getContractService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getHttpClient())
                .build();
        ContractService service = retrofit.create(ContractService.class);
        return service;
    }

    public UserService getUserService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getHttpClient())
                .build();
        UserService service = retrofit.create(UserService.class);
        return service;
    }

    public static OkHttpClient getHttpClient() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //TODO : remove logging interceptors as it is to be used for development purpose
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(300, TimeUnit.SECONDS)
                .readTimeout(300,TimeUnit.SECONDS).
                        addInterceptor(logging).
                        build();

        return client;
    }

    @Override
    public void run() {
        UserOut userOut = new UserOut();
        UserService service = getUserService();
        Call<UserOut> call = service.loginMobile(new User(id));
        try {
            userOut = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!userOut.token.isEmpty()) {
            Call<ContractOut> callContract;
            ContractService contractService = getContractServiceJWT(userOut.token);
            if (contractType.equals("erc721")) {
                callContract = contractService.addNft(contractNFT);
            } else {
                callContract = contractService.add(contract);
            }
            try {
                ContractOut contractOut = callContract.execute().body();
                ContractStatusOut status = new ContractStatusOut();

                Call<ContractStatusOut> callStatus;
                contractService = getContractService();
                ContractStatus contractStatus = new ContractStatus(contractOut.check_code);
                for (int i = 0; i < 7; i++) {
                    Thread.sleep(120000);
                    try {
                        callStatus = contractService.status(contractStatus);
                        status = callStatus.execute().body();
                        if (status.status)
                            break;
                    } catch (NullPointerException e) { }
                    catch (Exception e) { e.printStackTrace(); }
                }

                if (status.status) {
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

