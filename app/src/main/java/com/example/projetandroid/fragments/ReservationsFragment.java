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
import com.example.projetandroid.models.Reservation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ReservationsFragment extends Fragment {

    private RecyclerView recyclerView;
    private FloatingActionButton fabAddReservation;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private List<Reservation> reservationList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reservations, container, false);

        // Initialiser Firebase
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        // Initialiser les vues
        recyclerView = view.findViewById(R.id.recyclerViewReservations);
        fabAddReservation = view.findViewById(R.id.fabAddReservation);

        // Configuration RecyclerView
        reservationList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Charger les réservations
        loadReservations();

        // Listener FAB
        fabAddReservation.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Ajouter une réservation", Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    private void loadReservations() {
        String userId = mAuth.getCurrentUser().getUid();

        db.collection("reservations")
                .whereEqualTo("samsarId", userId)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    reservationList.clear();
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Reservation reservation = document.toObject(Reservation.class);
                        reservation.setReservationId(document.getId());
                        reservationList.add(reservation);
                    }
                    Toast.makeText(getContext(),
                            reservationList.size() + " réservations trouvées",
                            Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getContext(),
                            "Erreur: " + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                });
    }
}