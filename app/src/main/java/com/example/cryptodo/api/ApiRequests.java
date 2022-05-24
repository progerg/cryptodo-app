package com.example.cryptodo.api;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.cryptodo.api.in_models.User;
import com.example.cryptodo.api.out_models.UserOut;
import com.example.cryptodo.db.DB;


public class ApiRequests {
    private String baseUrl = "https://api.cryptodo.app/api/";

    public void registerUser(String id, DB mDBConnector) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserService service = retrofit.create(UserService.class);
        Call<UserOut> call = service.addMobile(new User(id));

        call.enqueue(new Callback<UserOut>() {
            @Override
            public void onResponse(Call<UserOut> call, Response<UserOut> response) {
                try {
                    UserOut userOut = response.body();
                    mDBConnector.insertUser(id);
                } catch (Exception e) {

                }
            }
            @Override
            public void onFailure(Call<UserOut> call, Throwable t) {
            }
        });
    }

    public void loginUser(String id, DB mDBConnector) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserService service = retrofit.create(UserService.class);
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