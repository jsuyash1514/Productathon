package com.example.android.gharkikheti;

import androidx.annotation.NonNull;
import com.google.firebase.database.ValueEventListener;

import androidx.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class FireBaseQueryLiveData extends LiveData<DataSnapshot> {
    private final Query query;
    private final MyValueEventListener listener =new MyValueEventListener();


    public FireBaseQueryLiveData(DatabaseReference ref){
        this.query=ref;
    }

    @Override
    protected void onActive() {
        query.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        query.removeEventListener(listener);
    }

    private class MyValueEventListener implements ValueEventListener{
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            setValue(dataSnapshot);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    }
}
