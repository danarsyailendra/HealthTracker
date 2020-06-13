package com.mercubuana.healthtracker.retrofit;

import com.mercubuana.healthtracker.response.ResponseLogin;
import com.mercubuana.healthtracker.response.ResponseRegister;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseRegister> register(
            @Field("email") String email,
            @Field("name") String name,
            @Field("password") String password,
            @Field("gender") String gender
    );

    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseLogin> login(
            @Field("email") String email,
            @Field("password") String password
    );
}
