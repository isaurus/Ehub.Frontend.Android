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

/**
 * 'ViewModel' que gestiona la lógica de la fase de autenticación en 'LoginFragment' y
 * 'RegistrationFragment'
 */
@HiltViewModel
public class AuthViewModel extends ViewModel {

    private static final int MIN_PASSWORD_LENGTH = 6;   // Para validación de contraseña

    private final LoginWithEmailUseCase loginWithEmailUseCase;
    private final LoginWithGoogleUseCase loginWithGoogleUseCase;
    private final RegisterWithEmailUseCase registerWithEmailUseCase;

    public final MutableLiveData<LoginViewState> loginViewState = new MutableLiveData<>();
    public final MutableLiveData<RegistrationViewState> registrationViewState = new MutableLiveData<>();

    /**
     * Constructor que inyecta con Hilt los 'UseCase' de la fase de autenticación.
     *
     * @param loginWithEmailUseCase 'UseCase' para login con correo y contraseña.
     * @param loginWithGoogleUseCase 'UseCase' para login con Google.
     * @param registerWithEmailUseCase 'UseCase' para el registro con correo y contraseña.
     */
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

    /**
     * Ejecuta el 'loginWithEmailUseCase' y actualiza el 'LoginViewState' en función del resultado de
     * la validación sobre el proceso.
     *
     * @param email El email recibido desde 'validateLoginForm'.
     * @param password La contraseña recibida desde 'validateLoginForm'.
     */
    public void loginWithEmail(String email, String password) {
        loginViewState.setValue(LoginViewState.loading());

        loginWithEmailUseCase.execute(email, password).observeForever(resource -> {
            switch (resource.getStatus()){
                case SUCCESS:
                    if (resource.getData() != null){
                        loginViewState.setValue(LoginViewState.success());
                    } else {
                        loginViewState.setValue(LoginViewState.error("Error inesperado"));
                    }
                    break;
                case ERROR:
                    loginViewState.setValue(LoginViewState.error(resource.getMessage()));
                    break;
                case LOADING:
                    loginViewState.setValue(LoginViewState.loading());
                    break;
            }
        });
    }

    /**
     * Ejecuta el 'registerWithEmailUseCase' y actualiza el 'RegistrationViewState' en función del
     * resultado de la validación sobre el proceso.
     *
     * @param email El email recibido desde 'validateRegisterForm'.
     * @param password La contraseña recibida desde 'validateRegisterForm'.
     */
    public void registerWithEmail(String email, String password){
        registrationViewState.setValue(RegistrationViewState.loading());

        registerWithEmailUseCase.execute(email, password).observeForever(resource -> {
            switch (resource.getStatus()){
                case SUCCESS:
                    if (resource.getData() != null){
                        registrationViewState.setValue(RegistrationViewState.success());
                    } else {
                        registrationViewState.setValue(RegistrationViewState.error("Respuesta inesperada del servidor"));
                    }
                    break;
                case ERROR:
                    registrationViewState.setValue(RegistrationViewState.error(resource.getMessage()));
                    break;
                case LOADING:
                    registrationViewState.setValue(RegistrationViewState.loading());
                    break;
            }
        });
    }

    /**
     * Ejecuta el 'loginWithGoogleUseCase' y actualiza el 'LoginViewState' en función del
     *      * resultado de la validación sobre el proceso.
     *
     * @param tokenId El token de Google para la autenticación con Firebase.
     */
    public void loginWithGoogle(String tokenId){
        loginViewState.setValue(LoginViewState.loading());

        loginWithGoogleUseCase.execute(tokenId).observeForever(resource -> {
            switch (resource.getStatus()){
                case SUCCESS:
                    if (resource.getData() != null){
                        loginViewState.setValue(LoginViewState.success());
                    } else {
                        loginViewState.setValue(LoginViewState.error("Respuesta inesperada del servidor"));
                    }
                    break;
                case ERROR:
                    loginViewState.setValue(LoginViewState.error(resource.getMessage()));
                    break;
                case LOADING:
                    loginViewState.setValue(LoginViewState.loading());
                    break;
            }
        });
    }

    /**
     * Lógica para validar el input del formulario de login.
     *
     * @param email El email introducido por el usuario.
     * @param password La contraseña introducida por el usuario.
     */
    public void validateLoginForm(String email, String password){
        boolean isEmailValid = isValidEmail(email);
        boolean isPasswordValid = isValidPassword(password);

        loginViewState.setValue(LoginViewState.validating(isEmailValid, isPasswordValid));

        if (isEmailValid && isPasswordValid){
            loginWithEmail(email, password);
        }
    }

    /**
     * Lógica para validar el input del formulario de registro.
     *
     * @param email El email introducido por el usuario.
     * @param password La contraseña introducida por el usuario.
     * @param confirmPassword La repetición de contraseña introducida por el usuario.
     */
    public void validateRegisterForm(String email, String password, String confirmPassword){
        boolean isEmailValid = isValidEmail(email);
        boolean isPasswordValid = isValidPassword(password);
        boolean isConfirmPasswordValid = isValidConfirmPassword(password, confirmPassword);

        registrationViewState.setValue(RegistrationViewState.validating(isEmailValid, isPasswordValid, isConfirmPasswordValid));

        if (isEmailValid && isPasswordValid && isConfirmPasswordValid){
            registerWithEmail(email, password);
        }
    }

    /**
     * Lógica de validación del campo email.
     *
     * @param email El email introducido.
     * @return El resultado (bool) de la validación.
     */
    private boolean isValidEmail(String email){
        return !email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * Lógica de validación del campo password.
     *
     * @param password La contraseña introducida.
     * @return El resultado (bool) de la validación.
     */
    private boolean isValidPassword(String password){
        return !password.isEmpty() && password.length() >= MIN_PASSWORD_LENGTH;
    }

    /**
     * Lógica de validación para la repetición de la contraseña.
     *
     * @param password La contraseña introducida.
     * @param confirmPassword La contraseña introducida de nuevo para confirmar.
     * @return El resultado (bool) de la validación.
     */
    private boolean isValidConfirmPassword(String password, String confirmPassword){
        return isValidPassword(password) && password.equals(confirmPassword);
    }
}
