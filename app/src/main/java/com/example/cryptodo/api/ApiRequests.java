package com.example.cryptodo.api;


import android.util.Log;

import java.io.IOException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.cryptodo.api.in_models.AddUser;
import com.example.cryptodo.api.out_models.AddUserOut;
import com.example.cryptodo.db.DB;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ApiRequests {
    private String baseUrl = "https://api.cryptodo.app/api/";

    public void registerUser(String id, DB mDBConnector) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserService service = retrofit.create(UserService.class);
        Call<AddUserOut> call = service.addMobile(new AddUser(id));

        call.enqueue(new Callback<AddUserOut>() {
            @Override
            public void onResponse(Call<AddUserOut> call, Response<AddUserOut> response) {
                try {
                    AddUserOut userOut = response.body();
                    mDBConnector.insertUser(id);
                } catch (Exception e) {

                }
            }
            @Override
            public void onFailure(Call<AddUserOut> call, Throwable t) {
            }
        });
    }

    public void loginUser(String id, DB mDBConnector) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserService service = retrofit.create(UserService.class);
        Call<AddUserOut> call = service.loginMobile(new AddUser(id));

        call.enqueue(new Callback<AddUserOut>() {
            @Override
            public void onResponse(Call<AddUserOut> call, Response<AddUserOut> response) {
                try {
                    AddUserOut userOut = response.body();
                } catch (Exception e) {

                }
            }
            @Override
            public void onFailure(Call<AddUserOut> call, Throwable t) {
            }
        });
    }

}