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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.gharkikheti.Adapter.AnswerAdapter;
import com.example.android.gharkikheti.Modal.AnswerModel;
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


public class AnswerFragment extends Fragment {
    RecyclerView recyclerView;
    TextView questionContent, questionPostedBy, questionTimestamp;
    AnswerAdapter adapter;
    private DatabaseReference reference;
    private ProgressDialog progressDialog;
    private Button button;
    private EditText edittext;
    private String timestamp;
    private FirebaseAuth firebaseAuth;

    public AnswerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_answer, container, false);

        button = view.findViewById(R.id.askAnswer);
        firebaseAuth = FirebaseAuth.getInstance();
        questionContent = view.findViewById(R.id.answer_question_content);
        questionPostedBy = view.findViewById(R.id.answer_question_postedBy);
        questionTimestamp = view.findViewById(R.id.answer_question_timestamp);
        recyclerView = view.findViewById(R.id.answer_recyclerView);
        reference = FirebaseDatabase.getInstance().getReference();

        questionContent.setText(getArguments().getString("questionDetailsContent"));
        questionPostedBy.setText(getArguments().getString("questionDetailsName"));
        timestamp = getArguments().getString("questionDetailsTimestamp");
        String req = "" + timestamp.charAt(6) + timestamp.charAt(7) + "/" + timestamp.charAt(4) + timestamp.charAt(5) + "/" + timestamp.charAt(0) + timestamp.charAt(1) + timestamp.charAt(2) + timestamp.charAt(3) + ", " + timestamp.charAt(8) + timestamp.charAt(9) + ":" + timestamp.charAt(10) + timestamp.charAt(11);
        questionTimestamp.setText(req);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Fetching data...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        ViewModelAnswer viewModel = ViewModelProviders.of(this).get(ViewModelAnswer.class);
        LiveData<DataSnapshot> liveData = viewModel.getdatasnapshotlivedata();
        liveData.observe(this, new Observer<DataSnapshot>() {
            @Override
            public void onChanged(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    final List<AnswerModel> list = new ArrayList<>();
                    adapter = new AnswerAdapter(list);
                    if (dataSnapshot.child(timestamp).hasChildren()) {
                        for (final DataSnapshot readdata : dataSnapshot.child(timestamp).getChildren()) {
                            Log.d("timestamp", readdata.getValue().toString());
                            if (readdata != null) {
                                String uid = readdata.child("uid").getValue().toString();
                                DatabaseReference databaseReference = reference.child(uid).child("profile").child("name");
                                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        String name = dataSnapshot.getValue().toString();
                                        String timestamp = readdata.getKey();
                                        String ts = "" + timestamp.charAt(6) + timestamp.charAt(7) + "/" + timestamp.charAt(4) + timestamp.charAt(5) + "/" + timestamp.charAt(0) + timestamp.charAt(1) + timestamp.charAt(2) + timestamp.charAt(3) + ", " + timestamp.charAt(8) + timestamp.charAt(9) + ":" + timestamp.charAt(10) + timestamp.charAt(11);
                                        Log.d("timecheck", timestamp);
                                        list.add(new AnswerModel(name, ts, readdata.child("answer").getValue(String.class)));
                                        adapter.notifyDataSetChanged();
                                        progressDialog.dismiss();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                        progressDialog.dismiss();
                                    }
                                });
                            } else progressDialog.dismiss();
                        }
                    }
                    else progressDialog.dismiss();
                    RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(adapter);
                } else progressDialog.dismiss();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(getContext());
                View dialogview = li.inflate(R.layout.answer_dialog, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setView(dialogview);
                edittext = dialogview.findViewById(R.id.add_answer);
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("ADD ANSWER",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                                        String ts = simpleDateFormat.format(new Date());
                                        reference.child("answers").child(timestamp).child(ts).child("uid").setValue(firebaseAuth.getCurrentUser().getUid());
                                        reference.child("answers").child(timestamp).child(ts).child("answer").setValue(edittext.getText().toString());
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

        return view;

    }
}
