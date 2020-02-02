package com.example.android.gharkikheti;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SpinnerAdapter;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AskQuestionFragment extends Fragment {
    private RecyclerView recyclerView;
    private QuestionAdapater adapter;
    private DatabaseReference reference;
    private Button button;
    private EditText edittext;
    private FirebaseAuth firebaseAuth;
    private String timestamp;
    private ProgressDialog progressDialog;

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
        firebaseAuth=FirebaseAuth.getInstance();
        button = view.findViewById(R.id.askQuestion);
        recyclerView = view.findViewById(R.id.question_recyclerView);
        reference = FirebaseDatabase.getInstance().getReference();
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Fetching data...");
        progressDialog.setCancelable(false);
        progressDialog.show();
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
                            DatabaseReference databaseReference = reference.child(uid).child("profile").child("name");
                            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    String name = dataSnapshot.getValue().toString();
                                    String timestamp = readdata.getKey();
                                    Log.d("timecheck", timestamp);
                                    list.add(new QuestionModel(readdata.child("question").getValue(String.class), name, timestamp));
                                    adapter.notifyDataSetChanged();
                                    progressDialog.dismiss();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    progressDialog.dismiss();
                                }
                            });
                        }
                        else progressDialog.dismiss();
                    }
                    RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(adapter);
                }
                else progressDialog.dismiss();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(getContext());
                View dialogview = li.inflate(R.layout.ask_question_dialog, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setView(dialogview);
                edittext = dialogview.findViewById(R.id.ask_question);
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("ASK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                                        timestamp = simpleDateFormat.format(new Date());
                                        reference.child("questions").child(timestamp).child("uid").setValue(firebaseAuth.getCurrentUser().getUid());
                                        reference.child("questions").child(timestamp).child("question").setValue(edittext.getText().toString());
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                AlertDialog alertDialog = alertDialogBuilder.create();

                alertDialog.show();
            }
        });
    }
}
