package com.isaac.ehub.ui.team;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.isaac.ehub.R;
import com.isaac.ehub.databinding.FragmentTeamContainerBinding;
import com.isaac.ehub.ui.ViewPagerAdapter;

public class TeamContainerFragment extends Fragment {

    private FragmentTeamContainerBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTeamContainerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Configurar ViewPager
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        binding.viewPager.setAdapter(adapter);

        // Vincular TabLayout con ViewPager
        new TabLayoutMediator(binding.tabLayout, binding.viewPager, (tab, position) -> {
            switch (position) {
                case 0: tab.setText("Calendar"); break;
                case 1: tab.setText("Tasks"); break;
                case 2: tab.setText("Members"); break;
            }
        }).attach();

        // Establecer MemberFragment como pestaña predeterminada (posición 2)
        binding.viewPager.setCurrentItem(2, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}