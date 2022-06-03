package com.example.cryptodo.api;

import com.example.cryptodo.api.in_models.NFTContract;
import com.example.cryptodo.api.in_models.SimpleContract;
import com.example.cryptodo.api.in_models.ContractStatus;
import com.example.cryptodo.api.out_models.ContractOut;
import com.example.cryptodo.api.out_models.ContractStatusOut;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ContractService {
    @POST("contract/add-nft")
    Call<ContractOut> addNft(@Body NFTContract nft);

    @POST("contract/add")
    Call<ContractOut> add(@Body SimpleContract simple);

    @POST("contract/status")
    Call<ContractStatusOut> status(@Body ContractStatus status);
}
