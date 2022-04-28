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
import okhttp3.Response;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ApiRequests {

    private class User {
        int id;
        int user_id;
        String username;
        String language;
        int balance;
        int referral_user_id;
        int first_lvl_ref;
        int second_lvl_ref;
        int third_lvl_ref;
    }

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final OkHttpClient client = new OkHttpClient();

    private final String main_url;
    private final String get_user_url;
    private final String contract_add_url;
    private final String add_user_url;
    private final String user_update_url;
    private final String user_ref_url;
    private final String contract_add_nft_url;
    private final String contract_status_url;

    public ApiRequests(String url) {
        this.main_url = url;
        this.get_user_url = url + "/user/get";
        this.contract_add_url = url + "/contract/add";
        this.add_user_url = url + "/user/add";
        this.user_update_url = url + "/user/update";
        this.user_ref_url = url + "/user/referral_deduction";
        this.contract_add_nft_url = url + "/contract/addnft";
        this.contract_status_url = url + "/contract/status";
    }

    private JSONObject postRequest(String url, JSONObject postBody) throws IOException, JSONException {
        RequestBody body = RequestBody.create(JSON, postBody.toString());

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        JSONObject data = new JSONObject(response.body().string());

        return data;
    }

//     public User getUser(int userId) throws JSONException, IOException {
//        JSONObject jsonObj = new JSONObject();
//        jsonObj.put("user_id", userId);
//        JSONObject answer = postRequest(get_user_url, jsonObj);
//        try {
//            User user = new User();
//            user.id = Integer.parseInt(answer.getString("id"));
//            user.user_id = answer.getInt("user_id");
//            user.username = answer.getString("username");
//            user.language = answer.getString("language");
//            user.balance = answer.getInt("balance");
//            user.referral_user_id = answer.getInt("referral_user_id");
//            user.first_lvl_ref = answer.getInt("first_lvl_ref");
//            user.second_lvl_ref = answer.getInt("second_lvl_ref");
//            user.third_lvl_ref = answer.getInt("third_lvl_ref");
//            return user;
//        }
//        catch (Exception e) {
//            return null;
//        }
//    }

    public JSONObject checkVerifyStatus(String checkCode) throws JSONException, IOException {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("check_code", checkCode);
        JSONObject answer = postRequest(contract_status_url, jsonObj);
        return answer;
    }

    public JSONObject contractAdd(String owner, int totalSupply, int decimals, String name,
                                  String title, String symbol, String node, boolean testMode,
                                  boolean burn, boolean mint, int feePercent,  String feeAddress,
                                  int taxBurningPercent, int stakingPercent, boolean forkSafemoon) throws JSONException, IOException {
        JSONObject json = new JSONObject();
        json.put("owner", owner);
        json.put("totalSupply", totalSupply);
        json.put("decimals", decimals);
        json.put("name", name);
        json.put("title", title);
        json.put("symbol", symbol);
        json.put("node", node);
        json.put("test_mode", testMode);
        json.put("burn", burn);
        json.put("mint", mint);
        json.put("fee_percent", feePercent);
        json.put("fee_address", feeAddress);
        json.put("tax_burning_percent", taxBurningPercent);
        json.put("staking_percent", stakingPercent);
        json.put("fork_safemoon", forkSafemoon);
        JSONObject answer = postRequest(contract_add_url, json);
        return answer;
    }

    public JSONObject contractAddNFT(String owner, int totalSupply, int decimals, String name,
                                  String title, String symbol, String node, boolean testMode,
                                  int tokenPerTx, int tokenPerWallet, float startPrice,
                                     String founder, String uri, boolean incrementMaxAmount,
                                     boolean presale, String timeForGrown) throws JSONException, IOException {
        JSONObject json = new JSONObject();
        json.put("owner", owner);
        json.put("totalSupply", totalSupply);
        json.put("decimals", decimals);
        json.put("name", name);
        json.put("title", title);
        json.put("symbol", symbol);
        json.put("node", node);
        json.put("test_mode", testMode);
        json.put("token_per_tx", tokenPerTx);
        json.put("token_per_wallet", tokenPerWallet);
        json.put("start_price", startPrice);
        json.put("founder", founder);
        json.put("increment_max_amount", incrementMaxAmount);
        json.put("presale", presale);
        json.put("time_for_grown", timeForGrown);
        json.put("uri", uri);
        JSONObject answer = postRequest(contract_add_url, json);
        return answer;
    }
}
