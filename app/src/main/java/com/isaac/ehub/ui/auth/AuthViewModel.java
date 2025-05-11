package com.isaac.ehub.ui.auth;

import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.isaac.ehub.domain.usecase.auth.LoginWithEmailUseCase;
import com.isaac.ehub.domain.usecase.auth.LoginWithGoogleUseCase;
import com.isaac.ehub.domain.usecase.auth.RegisterWithEmailUseCase;

import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AuthViewModel extends ViewModel {

    private static final int MIN_PASSWORD_LENGTH = 6;   // Para validación de contraseña

    private final LoginWithEmailUseCase loginWithEmailUseCase;
    private final LoginWithGoogleUseCase loginWithGoogleUseCase;
    private final RegisterWithEmailUseCase registerWithEmailUseCase;

    public final MutableLiveData<LoginViewState> loginViewState = new MutableLiveData<>();
    public final MutableLiveData<RegistrationViewState> registrationViewState = new MutableLiveData<>();

    @Inject
    public AuthViewModel(
            LoginWithEmailUseCase loginWithEmailUseCase,
            LoginWithGoogleUseCase loginWithGoogleUseCase,
            RegisterWithEmailUseCase registerWithEmailUseCase
    ){
        this.loginWithEmailUseCase = loginWithEmailUseCase;
        this.loginWithGoogleUseCase = loginWithGoogleUseCase;
        this.registerWithEmailUseCase = registerWithEmailUseCase;
    }

    public LiveData<LoginViewState> getLoginViewState(){
        return loginViewState;
    }

    public LiveData<RegistrationViewState> getRegistrationViewState(){
        return registrationViewState;
    }

    // Implementar los métodos de login y registro

    public void validateLoginForm(String email, String password){
        boolean isEmailValid = isValidEmail(email);
        boolean isPasswordValid = isPasswordValid(password);

        loginViewState.setValue(LoginViewState.validating(isEmailValid, isPasswordValid));

        if (isEmailValid && isPasswordValid){
            loginWithEmail(email, password);
        }
    }

    public void loginWithEmail(String email, String password) {
    }

    // Validación para Email
    private boolean isValidEmail(String email){
        return !email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    // Validación para Contraseña
    private boolean isPasswordValid(String password){
        return !password.isEmpty() && password.length() >= MIN_PASSWORD_LENGTH;
    }
}
