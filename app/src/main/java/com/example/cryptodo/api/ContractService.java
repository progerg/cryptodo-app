package com.example.cryptodo.api;

import com.example.cryptodo.api.in_models.AddNft;
import com.example.cryptodo.api.in_models.AddSimple;
import com.example.cryptodo.api.in_models.ContractStatus;
import com.example.cryptodo.api.out_models.ContractAddOut;
import com.example.cryptodo.api.out_models.ContractStatusOut;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ContractService {
    @POST("/add-nft")
    Call<ContractAddOut> addNft(@Body AddNft nft);

    @POST("/add")
    Call<ContractAddOut> add(@Body AddSimple simple);

    @POST("/status")
    Call<ContractStatusOut> status(@Body ContractStatus status);
}
