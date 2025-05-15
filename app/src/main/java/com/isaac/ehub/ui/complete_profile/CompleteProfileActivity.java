package com.isaac.ehub.ui.complete_profile;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.isaac.ehub.databinding.ActivityCompleteProfileBinding;
import com.isaac.ehub.domain.model.UserModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CompleteProfileActivity extends AppCompatActivity {

    private ActivityCompleteProfileBinding binding;
    private CompleteProfileViewModel completeProfileViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        completeProfileViewModel = new ViewModelProvider(this).get(CompleteProfileViewModel.class);
        binding = ActivityCompleteProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setUpListeners();
        observeViewModel();
    }

    /**
     * Configura el listener de la actividad para el botón de finalizar registro. Crea un nuevo 'UserModel'
     * basado en los inputs del usuario desde la UI y lanza una nueva actividad.
     */
    private void setUpListeners(){
        binding.btnPickAvatar.setOnClickListener(v -> {

        });

        binding.btnFinishRegistration.setOnClickListener(v -> {
            String avatarUrl = ""; // ¿CÓMO RECOJO LA URL?
            String firstName = binding.etFirstName.getText().toString().trim();
            String lastName = binding.etLastName.getText().toString().trim();
            String birthDate = binding.etBirthday.getText().toString().trim();  // ¿CÓMO MANEJO LAS FECHAS?
            String country = binding.actCountry.getText().toString().trim();    // ¿CÓMO MANEJO EL DESPLEGABLE?

            UserModel userModel = new UserModel(
                    avatarUrl,
                    firstName,
                    lastName,
                    birthDate,  // ¿?¿?¿?
                    country     // ¿?¿?¿?
            );

            completeProfileViewModel.completeUserProfile(userModel);

            // AÑADIR EL LANZAMIENTO (NAVEGACIÓN) A LA OTRA ACTIVITY
        });
    }

    private void observeViewModel(){
        completeProfileViewModel.getCompleteProfileViewState().observe(this, viewState -> {
            binding.progressBar.setVisibility(viewState.isLoading() ? View.VISIBLE : View.GONE);

            if (!viewState.isAvatarSelected()){
                // GESTIONAR EL ERROR EN LA SELECCIÓN DE AVATAR
                // PRETENDO QUE HAYA UNO PRESELECCIONADO
            } else {
                // GESTIONAR CUANDO NO HAY ERROR EN AVATAR
            }

            if (!viewState.isFirstNameValid()){
                binding.tilFirstName.setError("Nombre no válido");
            } else {
                binding.tilFirstName.setError(null);
            }

            if (!viewState.isLastNameValid()){
                binding.tilLastName.setError("Apellidos no válidos");
            } else {

            }binding.tilLastName.setError(null);

            if (!viewState.isBirthDateValid()){
                // GESTIONAR ERROR FECHA
            } else {
                // GESTIONAR ÉXITO FECHA
            }

            if (!viewState.isCountryValid()){
                // GESTIONAR ERROR PAÍS
            } else {
                // GESTIONAR ÉXITO PAÍS
            }
        });
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        binding = null;
    }
}