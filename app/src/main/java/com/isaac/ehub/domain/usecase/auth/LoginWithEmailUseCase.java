package com.isaac.ehub.domain.usecase.auth;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.isaac.ehub.domain.repository.AuthRepository;

import javax.inject.Inject;

/**
 * Caso de uso para el Login con correo y contraseña. La clase utiliza el repositorio
 * (/domain/repository/AuthRepository.java) para interactuar directamente con la base de datos,
 * de forma que la responsabilidad se delegue a esta clase en lugar de al ViewModel (que pertenece
 * a la capa de presentación).
 */
public class LoginWithEmailUseCase {

    private final AuthRepository authRepository;

    @Inject
    public LoginWithEmailUseCase(AuthRepository authRepository){
        this.authRepository = authRepository;
    }

    public Task<AuthResult> execute(String email, String password){
        return authRepository.loginWithEmail(email, password);
    }
}
