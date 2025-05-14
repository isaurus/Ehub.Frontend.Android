package com.isaac.ehub.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.isaac.ehub.R;

public class TeamContainerFragment extends Fragment {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el layout del contenedor (con TabLayout + ViewPager2)
        View view = inflater.inflate(R.layout.fragment_team_container, container, false);

        // Configurar ViewPager2
        viewPager = view.findViewById(R.id.view_pager);
        setupViewPager();

        // Configurar TabLayout y vincularlo con ViewPager2
        tabLayout = view.findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Calendario");
                    break;
                case 1:
                    tab.setText("Tareas");
                    break;
                case 2:
                    tab.setText("Miembros");
                    break;
            }
        }).attach();

        return view;
    }

    private void setupViewPager() {
        // Crear un adapter para el ViewPager2
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);
    }
}