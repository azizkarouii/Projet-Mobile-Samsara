package com.example.projetandroid.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projetandroid.R;
import com.example.projetandroid.models.User;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileFragment extends Fragment {

    private TextView nameText, emailText, phoneText;
    private MaterialButton editButton;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialiser Firebase
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Initialiser les vues
        nameText = view.findViewById(R.id.nameText);
        emailText = view.findViewById(R.id.emailText);
        phoneText = view.findViewById(R.id.phoneText);
        editButton = view.findViewById(R.id.editButton);

        // Charger les informations
        loadUserProfile();

        // Listener
        editButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Éditer le profil", Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    private void loadUserProfile() {
        String userId = mAuth.getCurrentUser().getUid();

        db.collection("users")
                .document(userId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        User user = documentSnapshot.toObject(User.class);
                        if (user != null) {
                            nameText.setText(user.getName());
                            emailText.setText(user.getEmail());
                            phoneText.setText(user.getPhone());
                        }
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getContext(),
                            "Erreur: " + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                });
    }
}