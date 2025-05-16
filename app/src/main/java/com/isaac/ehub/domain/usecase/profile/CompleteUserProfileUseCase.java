package com.isaac.ehub.domain.usecase.profile;

import androidx.lifecycle.LiveData;

import com.isaac.ehub.core.Resource;
import com.isaac.ehub.data.remote.api.UserRetrofitService;
import com.isaac.ehub.domain.model.UserModel;

import javax.inject.Inject;

public class CompleteUserProfileUseCase {

    private final UserRetrofitService userRetrofitService;

    @Inject
    public CompleteUserProfileUseCase(UserRetrofitService userRetrofitService){
        this.userRetrofitService = userRetrofitService;
    }

    public LiveData<Resource<Boolean>> execute(String firebaseIdToken, UserModel userModel) {
        String authHeader = "Bearer " + firebaseIdToken;
        return userRetrofitService.completeUserProfile(authHeader, userModel);
    }
}
