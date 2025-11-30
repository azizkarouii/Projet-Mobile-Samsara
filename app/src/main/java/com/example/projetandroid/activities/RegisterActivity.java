package com.example.projetandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetandroid.R;
import com.example.projetandroid.models.User;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText nameInput, phoneInput, emailInput, passwordInput, confirmPasswordInput;
    private MaterialButton registerButton;
    private ProgressBar progressBar;
    private TextView loginText;
    private ImageButton backButton;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialiser Firebase
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Initialiser les vues
        initViews();

        // Listeners
        registerButton.setOnClickListener(v -> registerUser());
        loginText.setOnClickListener(v -> {
            // Retour à LoginActivity
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
        backButton.setOnClickListener(v -> finish());
    }

    private void initViews() {
        nameInput = findViewById(R.id.nameInput);
        phoneInput = findViewById(R.id.phoneInput);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        confirmPasswordInput = findViewById(R.id.confirmPasswordInput);
        registerButton = findViewById(R.id.registerButton);
        progressBar = findViewById(R.id.progressBar);
        loginText = findViewById(R.id.loginText);
        backButton = findViewById(R.id.backButton);
    }

    private void registerUser() {
        String name = nameInput.getText().toString().trim();
        String phone = phoneInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        String confirmPassword = confirmPasswordInput.getText().toString().trim();

        // Validations
        if (TextUtils.isEmpty(name)) {
            nameInput.setError("Nom requis");
            nameInput.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(phone)) {
            phoneInput.setError("Téléphone requis");
            phoneInput.requestFocus();
            return;
        }

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

        if (!password.equals(confirmPassword)) {
            confirmPasswordInput.setError("Mots de passe différents");
            confirmPasswordInput.requestFocus();
            return;
        }

        // Afficher le chargement
        showLoading(true);

        // Créer l'utilisateur dans Firebase Authentication
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Inscription réussie
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        if (firebaseUser != null) {
                            // Créer l'objet User
                            User user = new User(firebaseUser.getUid(), name, email, phone);

                            // Sauvegarder dans Firestore
                            saveUserToFirestore(user);
                        }
                    } else {
                        showLoading(false);
                        Toast.makeText(RegisterActivity.this,
                                "Échec d'inscription: " + task.getException().getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void saveUserToFirestore(User user) {
        db.collection("users")
                .document(user.getUserId())
                .set(user) // Correction: passer l'objet user directement
                .addOnSuccessListener(aVoid -> {
                    showLoading(false);
                    Toast.makeText(RegisterActivity.this,
                            "Inscription réussie !", Toast.LENGTH_SHORT).show();
                    // Rediriger vers l'activité principale après une inscription réussie
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                })
                .addOnFailureListener(e -> {
                    showLoading(false);
                    Toast.makeText(RegisterActivity.this,
                            "Erreur lors de la sauvegarde: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                });
    }

    private void showLoading(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
            registerButton.setEnabled(false);
        } else {
            progressBar.setVisibility(View.GONE);
            registerButton.setEnabled(true);
        }
    }
}