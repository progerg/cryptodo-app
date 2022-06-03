package com.example.cryptodo.api;


import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.cryptodo.api.in_models.User;
import com.example.cryptodo.api.out_models.UserOut;
import com.example.cryptodo.db.DB;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;


public class ApiRequests {
    private String baseUrl = "https://api.cryptodo.app/api/";

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



    public void registerUser(String id, DB mDBConnector) {
        UserService service = getUserService();
        Call<UserOut> call = service.addMobile(new User(id));

        call.enqueue(new Callback<UserOut>() {
            @Override
            public void onResponse(Call<UserOut> call, Response<UserOut> response) {
                UserOut userOut = response.body();
                System.out.println(id);
                System.out.println(userOut.token);
                mDBConnector.insertUser(id);
            }
            @Override
            public void onFailure(Call<UserOut> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void loginUser(String id, DB mDBConnector) {
        UserService service = getUserService();
        Call<UserOut> call = service.loginMobile(new User(id));

        call.enqueue(new Callback<UserOut>() {
            @Override
            public void onResponse(Call<UserOut> call, Response<UserOut> response) {
                try {
                    UserOut userOut = response.body();
                } catch (Exception e) {

                }
            }
            @Override
            public void onFailure(Call<UserOut> call, Throwable t) {
            }
        });
    }

}