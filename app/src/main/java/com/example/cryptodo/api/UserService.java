package com.example.cryptodo.api;

import com.example.cryptodo.api.in_models.AddUser;
import com.example.cryptodo.api.out_models.AddUserOut;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {
    @GET("/login")
    Call<AddUserOut> login(@Body AddUser user);

    @POST("/add-mobile")
    Call<AddUserOut> addMobile(@Body AddUser user);

}
