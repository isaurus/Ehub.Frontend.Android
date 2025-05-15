package com.isaac.ehub.data.remote.api;

import androidx.lifecycle.LiveData;

import com.isaac.ehub.core.Resource;
import com.isaac.ehub.domain.model.UserModel;

import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserRetrofitService {
    @POST("users/{idUser}/complete-profile")
    LiveData<Resource<Boolean>> completeUserProfile(@Body UserModel userModel);
}
