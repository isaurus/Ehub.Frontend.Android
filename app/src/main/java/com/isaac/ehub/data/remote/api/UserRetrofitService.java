package com.isaac.ehub.data.remote.api;

import androidx.lifecycle.LiveData;

import com.isaac.ehub.core.Resource;
import com.isaac.ehub.domain.model.UserModel;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserRetrofitService {
    @POST("users/complete-profile")
    LiveData<Resource<Boolean>> completeUserProfile(
            @Header ("Authorization") String authHeader,
            @Body UserModel userModel);
}
