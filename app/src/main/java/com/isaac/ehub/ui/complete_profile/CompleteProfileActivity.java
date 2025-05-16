package com.isaac.ehub.ui.complete_profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.isaac.ehub.HomeActivity;
import com.isaac.ehub.databinding.ActivityCompleteProfileBinding;
import com.isaac.ehub.core.utils.DatePickerUtils;

import java.util.Calendar;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CompleteProfileActivity extends AppCompatActivity {

    private ActivityCompleteProfileBinding binding;
    private CompleteProfileViewModel completeProfileViewModel;
    private Calendar calendar;
    private String selectedBirthDateIso; // Almacena la fecha en formato ISO

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        completeProfileViewModel = new ViewModelProvider(this).get(CompleteProfileViewModel.class);
        binding = ActivityCompleteProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        calendar = Calendar.getInstance();
        setupDatePicker();
        setUpListeners();
        observeViewModel();
    }

    private void setupDatePicker() {
        // Configurar límites de fecha (ejemplo: mayores de 18 años)
        Calendar minDate = Calendar.getInstance();
        minDate.add(Calendar.YEAR, -120); // 120 años atrás como mínimo

        Calendar maxDate = Calendar.getInstance();
        maxDate.add(Calendar.YEAR, -16); // Máximo 18 años atrás

        DatePickerUtils.setUpDatePicker(
                binding.etBirthday,
                maxDate, // Fecha inicial mostrada (18 años atrás)
                minDate,
                maxDate,
                new DatePickerUtils.DatePickerListener() {
                    @Override
                    public void onDateSelected(String isoDate, String displayDate) {
                        binding.etBirthday.setText(displayDate);
                        selectedBirthDateIso = isoDate;
                    }
                }
        );
    }
    private void setUpListeners() {
        binding.btnPickAvatar.setOnClickListener(v -> {
            // TODO: Implementar lógica para seleccionar avatar
            // AQUÍ DEBO DE COGER LA URL DE EL BACKEND
            Toast.makeText(this, "Seleccionar avatar", Toast.LENGTH_SHORT).show();
        });

        binding.btnFinishRegistration.setOnClickListener(v -> {
            String firstName = binding.etFirstName.getText().toString().trim();
            String lastName = binding.etLastName.getText().toString().trim();
            String country = binding.actCountry.getText().toString().trim();

            // Validar campos antes de crear el UserModel
            completeProfileViewModel.validateFields(
                    "url",     // FALTA IMPLEMENTARLO CON EL GET
                    firstName,
                    lastName,
                    selectedBirthDateIso,
                    "avatar"      // FALTA IMPLEMENTARLO EN EL GET
            );
        });
    }

    private void observeViewModel() {
        completeProfileViewModel.getCompleteProfileViewState().observe(this, viewState -> {
            binding.progressBar.setVisibility(viewState.isLoading() ? View.VISIBLE : View.GONE);

            // Manejo de errores de validación
            binding.tilFirstName.setError(viewState.isFirstNameValid() ? null : "Nombre no válido");
            binding.tilLastName.setError(viewState.isLastNameValid() ? null : "Apellidos no válidos");
            binding.tilBirthdate.setError(viewState.isBirthDateValid() ? null : "Fecha no válida");
            binding.tilCountry.setError(viewState.isCountryValid() ? null : "País no válido");

            // Avatar (preseleccionado por defecto)
            if (!viewState.isAvatarSelected()) {
                // TODO: Mostrar error si no hay avatar seleccionado
            }

            // Cuando todo es válido y el registro es exitoso
            if (viewState.isSuccess()) {
                startActivity(new Intent(this, HomeActivity.class));
                finish();
            }

            // Manejo de errores generales
            if (viewState.isError()) {
                Toast.makeText(this, viewState.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}