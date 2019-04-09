package com.ze.retrofit2;

import com.alibaba.fastjson.JSONObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface HttpService {
    @GET("swagger.json")
    Call<JSONObject> getSwagger() ;

    @GET
    Call<JSONObject> getSwaggerUrl(@Url String url) ;
}
