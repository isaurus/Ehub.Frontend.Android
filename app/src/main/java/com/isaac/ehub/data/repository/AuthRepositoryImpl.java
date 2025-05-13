package com.isaac.ehub.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.isaac.ehub.core.Resource;
import com.isaac.ehub.data.remote.api.RetrofitService;
import com.isaac.ehub.data.remote.model.AuthResponse;
import com.isaac.ehub.data.remote.model.LoginRequest;
import com.isaac.ehub.data.remote.model.RegisterRequest;
import com.isaac.ehub.domain.repository.AuthRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class AuthRepositoryImpl implements AuthRepository {

    private final FirebaseAuth firebaseAuth;

    @Inject
    public AuthRepositoryImpl(FirebaseAuth firebaseAuth){
        this.firebaseAuth = firebaseAuth;
    }

    @Override
    public LiveData<Resource<Boolean>> loginWithEmail(String email, String password) {
        MutableLiveData<Resource<Boolean>> result = new MutableLiveData<>();
        result.setValue(Resource.loading());

        retrofit.login(new LoginRequest(email, password)).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    result.setValue(Resource.success(true));
                } else {
                    result.setValue(Resource.error("Error"));
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable throwable) {
                result.setValue(Resource.error("Error de red: " + throwable.getMessage()));
            }
        });

        return result;

    }

    @Override
    public LiveData<Resource<Boolean>> loginWithGoogle(String idToken) {
        return null;
    }

    @Override
    public LiveData<Resource<Boolean>> registerWithEmail(String email, String password) {
        MutableLiveData<Resource<Boolean>> result = new MutableLiveData<>();
        result.setValue(Resource.loading());

        retrofit.register(new RegisterRequest(email, password)).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    result.setValue(Resource.success(true));
                } else {
                    result.setValue(Resource.error("Error en fase de registro"));
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable throwable) {
                result.setValue(Resource.error("Error de red: " + throwable.getMessage()));
            }
        });

        return result;
    }
}
