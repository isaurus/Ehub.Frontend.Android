package com.isaac.ehub.data.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.isaac.ehub.domain.repository.FirebaseRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FirebaseRepositoryImpl implements FirebaseRepository {

    private final FirebaseAuth firebaseAuth;

    @Inject
    public FirebaseRepositoryImpl(FirebaseAuth firebaseAuth){
        this.firebaseAuth = firebaseAuth;
    }

    @Override
    public void getIdToken(IdTokenCallback callback) {
        if(firebaseAuth.getCurrentUser() != null){
            firebaseAuth.getCurrentUser().getIdToken(true)
                    .addOnSuccessListener(result -> callback.onTokenReceived(result.getToken()))
                    .addOnFailureListener(e -> callback.onError(e.getMessage()));
        } else {
            callback.onError("Usuario no autenticado");
        }
    }
}
