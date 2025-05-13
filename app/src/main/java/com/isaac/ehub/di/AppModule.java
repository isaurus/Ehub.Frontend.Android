package com.isaac.ehub.di;

import com.isaac.ehub.data.remote.api.RetrofitService;
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
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Módulo de Hilt para proveer dependencias.
 */
@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    private static final String BASE_URL = "http://10.0.2.2:5048/"; // Cambia por tu URL base

    @Provides
    @Singleton
    public static OkHttpClient provideHttpClient(){
        return new OkHttpClient.Builder()
                .build();
    }

    @Provides
    @Singleton
    public static Retrofit provideRetrofit(OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public static RetrofitService provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(RetrofitService.class);
    }

    @Provides
    @Singleton
    public static AuthRepository provideAuthRepository(RetrofitService retrofit){
        return new AuthRepositoryImpl(retrofit);
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
