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
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.gharkikheti.Adapter.QuestionAdapater;
import com.example.android.gharkikheti.Modal.QuestionModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class AskQuestionFragment extends Fragment {
    private RecyclerView recyclerView;
    private QuestionAdapater adapter;
    private DatabaseReference reference;

    public AskQuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ask_question, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.question_recyclerView);
        reference = FirebaseDatabase.getInstance().getReference();
        ViewModel viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        LiveData<DataSnapshot> liveData = viewModel.getdatasnapshotlivedata();
        liveData.observe(this, new Observer<DataSnapshot>() {
            @Override
            public void onChanged(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    final List<QuestionModel> list = new ArrayList<>();
                    adapter = new QuestionAdapater(getContext(), list, getActivity());
                    for (final DataSnapshot readdata : dataSnapshot.getChildren()) {
                        if (readdata != null) {
                            String uid = readdata.child("uid").getValue().toString();
                            DatabaseReference databaseReference=reference.child(uid).child("profile").child("name");
                                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    String name = dataSnapshot.getValue().toString();
                                    String timestamp = readdata.getKey();
                                    Log.d("timecheck", timestamp);
                                    String req = "" + timestamp.charAt(6) + timestamp.charAt(7) + "/" + timestamp.charAt(4) + timestamp.charAt(5) + "/" + timestamp.charAt(0) + timestamp.charAt(1) + timestamp.charAt(2) + timestamp.charAt(3)+  ", "  + timestamp.charAt(8) + timestamp.charAt(9) + ":" + timestamp.charAt(10) + timestamp.charAt(11);
                                    list.add(new QuestionModel(readdata.child("question").getValue(String.class), name, req));
                                    adapter.notifyDataSetChanged();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }
                    RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(adapter);
                }
            }
        });
    }
}
