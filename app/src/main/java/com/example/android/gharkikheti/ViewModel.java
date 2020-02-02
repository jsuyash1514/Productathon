package com.example.android.gharkikheti;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewModel extends androidx.lifecycle.ViewModel {
    private static final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("questions");
    private final FireBaseQueryLiveData fireBaseQueryLiveData = new FireBaseQueryLiveData(ref);

    @NonNull
    public LiveData<DataSnapshot> getdatasnapshotlivedata() {
        return fireBaseQueryLiveData;
    }
}

