package com.example.cnpm;

import com.example.cnpm.model.ResultLogin;
import com.example.cnpm.model.ResultLogout;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface HerokuService {
    @GET("api/v1/logout")
    Call<ResultLogout> logout(@Header("Access-Token") String accesstoken);
    @POST("api/v1/signup")
    Call<ResultLogout> signup(@Body Register register);
    @POST("api/v1/login")
    Call<ResultLogin> login(@Body User user);
}
