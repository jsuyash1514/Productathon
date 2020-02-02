package com.example.android.gharkikheti;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class FeedFragment extends Fragment {
    private FirebaseAuth firebaseAuth;
    private Button logout;
    private NavController navController;

    public FeedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logout=view.findViewById(R.id.logout);
        navController= Navigation.findNavController(getActivity(),R.id.my_nav_host_fragment);
        firebaseAuth=FirebaseAuth.getInstance();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                navController.navigate(R.id.action_landingFragment_to_loginFragment);
            }
        });

    }
}
