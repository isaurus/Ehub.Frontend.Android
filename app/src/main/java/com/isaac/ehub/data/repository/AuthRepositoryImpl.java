package com.isaac.ehub.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.isaac.ehub.core.Resource;
import com.isaac.ehub.domain.repository.AuthRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

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

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> result.setValue(Resource.success(true)))
                .addOnFailureListener(e -> result.setValue(Resource.error(e.getMessage(), false)));

        return result;
    }

    @Override
    public LiveData<Resource<Boolean>> loginWithGoogle(String idToken) {
        return null;
    }

    @Override
    public LiveData<Resource<Boolean>> registerWithEmail(String email, String password) {
        return null;
    }
}
