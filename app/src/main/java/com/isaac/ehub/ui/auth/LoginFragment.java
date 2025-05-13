package com.isaac.ehub.ui.auth;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.isaac.ehub.HomeActivity;
import com.isaac.ehub.R;
import com.isaac.ehub.databinding.FragmentLoginBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private AuthViewModel authViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        authViewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpListeners();
        observeViewModel();
    }

    private void setUpListeners(){
        binding.btnLogin.setOnClickListener(v ->{
            String email = binding.etEmail.getText().toString().trim();
            String password = binding.etPassword.getText().toString().trim();

            authViewModel.validateLoginForm(email, password);
        });

        binding.tvRegister.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_registrationFragment);
        });

    }

    private void observeViewModel(){
        authViewModel.getLoginViewState().observe(getViewLifecycleOwner(), viewState -> {
            binding.progressBar.setVisibility(viewState.isLoading() ? View.VISIBLE : View.GONE);

            if (!viewState.isEmailValid()){
                binding.tilEmail.setError("Email no válido");
            } else{
                binding.tilEmail.setError(null);
            }

            if (!viewState.isPasswordValid()){
                binding.tilPassword.setError("Contraseña inválida");
            } else{
                binding.tilPassword.setError(null);
            }

            if (viewState.isSuccess()){
                requireActivity().startActivity(new Intent(requireContext(), HomeActivity.class));
                requireActivity().finish();
            }
        });
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }
}