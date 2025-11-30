package com.example.projetandroid.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetandroid.R;
import com.example.projetandroid.adapters.PropertyAdapter;
import com.example.projetandroid.models.Property;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class PropertiesFragment extends Fragment {

    private RecyclerView recyclerView;
    private PropertyAdapter propertyAdapter;
    private FloatingActionButton fabAddProperty;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private List<Property> propertyList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_properties, container, false);

        // Initialiser Firebase
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        // Initialiser les vues
        recyclerView = view.findViewById(R.id.recyclerViewProperties);
        fabAddProperty = view.findViewById(R.id.fabAddProperty);

        // Configuration RecyclerView
        propertyList = new ArrayList<>();
        propertyAdapter = new PropertyAdapter(propertyList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(propertyAdapter);

        // Charger les propriétés
        loadProperties();

        // Listener FAB
        fabAddProperty.setOnClickListener(v -> {
            // TODO: Implémenter l'ajout d'une propriété
            Toast.makeText(getContext(), "Ajouter une propriété", Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    private void loadProperties() {
        String userId = mAuth.getCurrentUser().getUid();

        db.collection("properties")
                .whereArrayContains("samsars", userId)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    propertyList.clear();
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Property property = document.toObject(Property.class);
                        property.setPropertyId(document.getId());
                        propertyList.add(property);
                    }
                    propertyAdapter.notifyDataSetChanged(); // Notifier l'adapter
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getContext(),
                            "Erreur: " + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                });
    }
}