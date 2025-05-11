package com.isaac.ehub.ui.auth;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.isaac.ehub.databinding.FragmentLoginBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private AuthViewModel authViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}