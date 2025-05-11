package com.isaac.ehub.di;

import com.google.firebase.auth.FirebaseAuth;
import com.isaac.ehub.data.repository.AuthRepositoryImpl;
import com.isaac.ehub.domain.repository.AuthRepository;
import com.isaac.ehub.domain.usecase.auth.LoginWithEmailUseCase;
import com.isaac.ehub.domain.usecase.auth.LoginWithGoogleUseCase;
import com.isaac.ehub.domain.usecase.auth.RegisterWithEmailUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

/**
 * Módulo de Hilt para proveer dependencias.
 */
@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public static FirebaseAuth provideFirebaseAuth(){
        return FirebaseAuth.getInstance();
    }

    @Provides
    @Singleton
    public static AuthRepository provideAuthRepository(FirebaseAuth firebaseAuth){
        return new AuthRepositoryImpl(firebaseAuth);
    }

    @Provides
    @Singleton
    public static LoginWithEmailUseCase provideLoginWithEmailUseCase(AuthRepository authRepository){
        return new LoginWithEmailUseCase(authRepository);
    }

    @Provides
    @Singleton
    public static LoginWithGoogleUseCase provideLoginWithGoogleUseCase(AuthRepository authRepository){
        return new LoginWithGoogleUseCase(authRepository);
    }

    @Provides
    @Singleton
    public static RegisterWithEmailUseCase provideRegisterWithEmailUseCase(AuthRepository authRepository){
        return new RegisterWithEmailUseCase(authRepository);
    }
}
