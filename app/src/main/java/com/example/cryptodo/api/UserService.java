package com.example.cryptodo.api;

import com.example.cryptodo.api.in_models.User;
import com.example.cryptodo.api.out_models.UserOut;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("user/login-mobile")
    Call<UserOut> loginMobile(@Body User user);

    @POST("user/add-mobile")
    Call<UserOut> addMobile(@Body User user);

}
