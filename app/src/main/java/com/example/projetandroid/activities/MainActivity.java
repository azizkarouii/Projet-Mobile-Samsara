package com.example.projetandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.projetandroid.R;
import com.example.projetandroid.fragments.ProfileFragment;
import com.example.projetandroid.fragments.PropertiesFragment;
import com.example.projetandroid.fragments.ReservationsFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private MaterialToolbar toolbar;
    private BottomNavigationView bottomNavigation;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialiser Firebase
        mAuth = FirebaseAuth.getInstance();

        // Vérifier si l'utilisateur est connecté
        if (mAuth.getCurrentUser() == null) {
            navigateToLogin();
            return;
        }

        // Initialiser les vues
        toolbar = findViewById(R.id.toolbar);
        bottomNavigation = findViewById(R.id.bottomNavigation);

        // Configurer la toolbar
        setSupportActionBar(toolbar);

        // Charger le fragment par défaut
        if (savedInstanceState == null) {
            loadFragment(new PropertiesFragment());
        }

        // Listener pour la navigation
        bottomNavigation.setOnItemSelectedListener(item -> {
            Fragment fragment = null;
            int itemId = item.getItemId();

            if (itemId == R.id.nav_properties) {
                fragment = new PropertiesFragment();
            } else if (itemId == R.id.nav_reservations) {
                fragment = new ReservationsFragment();
            } else if (itemId == R.id.nav_profile) {
                fragment = new ProfileFragment();
            }

            return loadFragment(fragment);
        });
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_logout) {
            logout();
            return true;
        } else if (itemId == R.id.action_search) {
            // TODO: Implémenter la recherche
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        mAuth.signOut();
        navigateToLogin();
    }

    private void navigateToLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}