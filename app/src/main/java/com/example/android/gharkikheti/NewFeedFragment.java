package com.example.android.gharkikheti;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class NewFeedFragment extends Fragment {

    private static final int PICK_VIDEO_REQUEST = 1;
    private static final int RESULT_CANCELED = 0;

    private VideoView videoView;
    private Button chooseBn, uploadBn;
    private Uri filePath;
    private EditText description;
    private StorageReference storageReference;
    private FirebaseDatabase db;


    public NewFeedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_feed, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

         videoView = view.findViewById(R.id.video_view);
         chooseBn = view.findViewById(R.id.choose_bn);
         uploadBn = view.findViewById(R.id.upload_bn);
         description = view.findViewById(R.id.video_description);

         db = FirebaseDatabase.getInstance();
         storageReference = FirebaseStorage.getInstance().getReference();

         chooseBn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                showFileChooser();
             }
         });

         uploadBn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                // uploadFile;
             }
         });


    }

    //method to show file chooser
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Video"),PICK_VIDEO_REQUEST );
    }

    //handling the image chooser activity
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_VIDEO_REQUEST && resultCode != RESULT_CANCELED && data != null && data.getData() != null) {
            //filePath = data.getData();
            Uri video = data.getData();
            videoView.setVideoURI(video);
            videoView.start();
        }
    }

}
