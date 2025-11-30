package com.example.projetandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetandroid.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText emailInput, passwordInput;
    private MaterialButton loginButton;
    private ProgressBar progressBar;
    private TextView registerText, forgotPasswordText;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialiser Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialiser les vues
        initViews();

        // Vérifier si l'utilisateur est déjà connecté
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            navigateToMain();
        }

        // Listeners
        loginButton.setOnClickListener(v -> loginUser());
        registerText.setOnClickListener(v -> navigateToRegister());
        forgotPasswordText.setOnClickListener(v -> resetPassword());
    }

    private void initViews() {
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);
        progressBar = findViewById(R.id.progressBar);
        registerText = findViewById(R.id.registerText);
        forgotPasswordText = findViewById(R.id.forgotPasswordText);
    }

    private void loginUser() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        // Validation
        if (TextUtils.isEmpty(email)) {
            emailInput.setError("Email requis");
            emailInput.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            passwordInput.setError("Mot de passe requis");
            passwordInput.requestFocus();
            return;
        }

        if (password.length() < 6) {
            passwordInput.setError("Minimum 6 caractères");
            passwordInput.requestFocus();
            return;
        }

        // Afficher le chargement
        showLoading(true);

        // Connexion Firebase
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    showLoading(false);

                    if (task.isSuccessful()) {
                        // Connexion réussie
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(LoginActivity.this,
                                "Bienvenue !", Toast.LENGTH_SHORT).show();
                        navigateToMain();
                    } else {
                        // Connexion échouée
                        Toast.makeText(LoginActivity.this,
                                "Échec de connexion: " + task.getException().getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void resetPassword() {
        String email = emailInput.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            emailInput.setError("Entrez votre email");
            emailInput.requestFocus();
            return;
        }

        showLoading(true);

        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {
                    showLoading(false);

                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this,
                                "Email de réinitialisation envoyé",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this,
                                "Erreur: " + task.getException().getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void showLoading(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
            loginButton.setEnabled(false);
        } else {
            progressBar.setVisibility(View.GONE);
            loginButton.setEnabled(true);
        }
    }

    private void navigateToMain() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void navigateToRegister() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}