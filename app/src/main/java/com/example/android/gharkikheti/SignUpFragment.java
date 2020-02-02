package com.example.android.gharkikheti;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpFragment extends Fragment {
    private EditText mName, mNumber, memail, mPassword;
    private Button mStudentRegister;
    private FirebaseAuth fauth;
    private ProgressDialog progressDialog;
    private NavController navController;
    private DatabaseReference ref;

    public SignUpFragment() {
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
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mName = view.findViewById(R.id.name);
        mNumber = view.findViewById(R.id.number);
        memail = view.findViewById(R.id.owner_email);
        mPassword = view.findViewById(R.id.owner_password);
        mStudentRegister = view.findViewById(R.id.owner_register);
        progressDialog = new ProgressDialog(getContext());
        fauth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference();
        navController = Navigation.findNavController(getActivity(), R.id.my_nav_host_fragment);
        mStudentRegister.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                final String name = mName.getText().toString().trim();
                final String number = mNumber.getText().toString().trim();
                final String emailid = memail.getText().toString().trim();
                final String password = mPassword.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    mName.setError("This field cannot be empty");
                } else if (TextUtils.isEmpty(number)) {
                    mNumber.setError("This field cannot be empty");
                } else if (TextUtils.isEmpty(emailid)) {
                    memail.setError("Email is required");
                } else if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is required");
                } else {
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.setMessage("Registering please wait....");
                    progressDialog.show();
                    fauth.createUserWithEmailAndPassword(emailid, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                DatabaseReference databaseReference = ref.child("user").child(fauth.getCurrentUser().getUid()).child("profile");
                                databaseReference.child("name").setValue(name);
                                databaseReference.child("mobile number").setValue(number);
                                databaseReference.child("email").setValue(emailid);
                                Toast.makeText(getContext(), "Registered successfully", Toast.LENGTH_SHORT).show();
                                navController.navigate(R.id.action_signUpFragment_to_feedFragment);
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(getContext(), "Registration unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}