package com.isaac.ehub.data.remote.api;

import com.isaac.ehub.data.remote.model.AuthResponse;
import com.isaac.ehub.data.remote.model.LoginRequest;
import com.isaac.ehub.data.remote.model.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitService {
    @POST("auth/login")
    Call<AuthResponse> login(@Body LoginRequest request);

    @POST("auth/register")
    Call<AuthResponse> register(@Body RegisterRequest request);
}
