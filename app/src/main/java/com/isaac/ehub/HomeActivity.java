package com.isaac.ehub;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.isaac.ehub.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding; // Objeto de View Binding
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        // Inflar el layout usando View Binding
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configurar NavController con el NavHostFragment
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        // Vincular BottomNavigationView con NavController usando View Binding
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController);

        // Opcional: Personalizar comportamiento al seleccionar ítems
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.soloQFragment) {
                navController.navigate(R.id.soloQFragment);
                return true;
            } else if (itemId == R.id.teamContainerFragment) {
                navController.navigate(R.id.teamContainerFragment);
                return true;
            } else if (itemId == R.id.achievementFragment) {
                navController.navigate(R.id.achievementFragment);
                return true;
            }
            return false;
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null; // Limpiar la referencia de View Binding
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}