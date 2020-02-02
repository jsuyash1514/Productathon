package com.example.android.gharkikheti;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.gharkikheti.Adapter.QuestionAdapater;
import com.example.android.gharkikheti.Modal.QuestionModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class QuestionFragment extends Fragment {
    private DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private QuestionAdapater adapter;

    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.question_recyclerView);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        ViewModel viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        LiveData<DataSnapshot> liveData = viewModel.getdatasnapshotlivedata();
        liveData.observe(this, new Observer<DataSnapshot>() {
            @Override
            public void onChanged(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    List<QuestionModel> list = new ArrayList<>();
                    adapter = new QuestionAdapater(list);
                    for (DataSnapshot readdata : dataSnapshot.getChildren()) {
                        String timestamp=readdata.getKey();
                        Log.d("timestamp",timestamp);
                        //list.add(new QuestionModel(readdata.child("timestamp").child("questions").getValue(),readdata.child("")readdata.getKey());
                        adapter.notifyDataSetChanged();
                    }
                    RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(adapter);
                }
            }
        });
    }
}
