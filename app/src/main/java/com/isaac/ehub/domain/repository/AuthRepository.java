package com.isaac.ehub.domain.repository;

import androidx.credentials.GetCredentialRequest;
import androidx.lifecycle.LiveData;

import com.isaac.ehub.core.Resource;

/**
 * Repositorio para la autenticación de usuarios. Permite:
 *      - Registro con correo y contraseña.
 *      - Login con correo y contraseña.
 *      - Login con Google.
 * Usa LiveData para operaciones asíncronas y la clase Resource (/core/Resource.java) para gestionar
 * el estado de las operaciones.
 */
public interface AuthRepository {
    LiveData<Resource<Boolean>> registerWithEmail(String email, String password);
    LiveData<Resource<Boolean>> loginWithEmail(String email, String password);
    LiveData<Resource<Boolean>> loginWithGoogle(String idToken);
}
