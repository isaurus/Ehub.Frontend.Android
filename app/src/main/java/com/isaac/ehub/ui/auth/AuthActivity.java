package com.isaac.ehub.ui.auth;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.isaac.ehub.databinding.ActivityAuthBinding;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * Esta clase es la encargada de gestionar los Fragments para el Login (LoginFragment)
 * y el Registro (RegistrationFragment). Gracias al NavHostFragment de AndroidX y el gráfico
 * de navegación para la autenticación (/res/navigation/nav_auth.xml), la Activity es capaz
 * de moverse entre los fragmentos fácilmente.
 */
@AndroidEntryPoint
public class AuthActivity extends AppCompatActivity {

    private ActivityAuthBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    // Listeners (para el tema del teclado, etc.)
    // Observers
}