package com.isaac.ehub.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
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
    public Task<AuthResult> registerWithEmail(String email, String password) {
        return firebaseAuth.createUserWithEmailAndPassword(email, password);
    }

    @Override
    public Task<AuthResult> loginWithEmail(String email, String password){
        return firebaseAuth.signInWithEmailAndPassword(email, password);
    }


    @Override
    public Task<AuthResult> loginWithGoogle(String idToken){
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        return firebaseAuth.signInWithCredential(credential);
    }


}
