package com.isaac.ehub.domain.repository;

public interface FirebaseRepository {
    void getIdToken(IdTokenCallback callback);


    interface IdTokenCallback {
        void onTokenReceived(String token);
        void onError(String error);
    }
}
