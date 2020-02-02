package com.example.android.gharkikheti;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginFragment extends Fragment {
    private EditText memail, mpassword;
    private TextView msignup;
    private FirebaseAuth fAuth;
    private Button mlogin;
    private NavController navController;
    private ProgressDialog progressDialog;

    public LoginFragment() {
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
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        msignup=view.findViewById(R.id.signUp);
        mlogin = view.findViewById(R.id.login_btn);
        memail = view.findViewById(R.id.email_id);
        mpassword = view.findViewById(R.id.password);
        fAuth = FirebaseAuth.getInstance();
        navController= Navigation.findNavController(getActivity(),R.id.my_nav_host_fragment);
        checkUserLoggedIn();
        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = memail.getText().toString().trim();
                String password = mpassword.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    memail.setError("Email is Required");
                    return;
                } else if (TextUtils.isEmpty(password)) {
                    mpassword.setError("Password is Required");
                    return;
                }
                progressDialog = new ProgressDialog(getContext());
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setMessage("Logging in please wait....");
                progressDialog.show();
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "login successful", Toast.LENGTH_SHORT).show();
                            navController.navigate(R.id.action_loginFragment_to_feedFragment);
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "Login unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        msignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_loginFragment_to_signUpFragment);
            }
        });
    }
    public void checkUserLoggedIn() {
        if (fAuth.getCurrentUser() != null) {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Logging in please wait....");
            progressDialog.show();
                    NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.loginFragment, true).build();
                    progressDialog.dismiss();
                        navController.navigate(R.id.action_loginFragment_to_feedFragment, null, navOptions);
                }
            }
}
