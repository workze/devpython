package com.ze.retrofit2;

import com.alibaba.fastjson.JSONObject;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class RetrofitTest {
    public static void main(String[] args) throws IOException {
        System.out.println("hi");

        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://petstore.swagger.io/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


        HttpService service = retrofit.create(HttpService.class);
//        retrofit2.Response<JSONObject> res = service.getSwagger().execute();
        retrofit2.Response<JSONObject> res = service.getSwaggerUrl("http://petstore.swagger.io/v2/swagger.json").execute();
        System.out.println("");
    }
}
