package com.isaac.ehub.domain.repository;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

/**
 * Repositorio para la autenticación de usuarios. Permite:
 *      - Registro con correo y contraseña.
 *      - Login con correo y contraseña.
 *      - Login con Google.
 * Usa LiveData para operaciones asíncronas y la clase Resource (/core/Resource.java) para gestionar
 * el estado de las operaciones.
 */
public interface AuthRepository {
    Task<AuthResult> registerWithEmail(String email, String password);
    Task<AuthResult> loginWithEmail(String email, String password);
    Task<AuthResult> loginWithGoogle(String token);
}
