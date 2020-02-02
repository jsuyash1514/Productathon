package com.example.android.gharkikheti;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;


public class NewFeedFragment extends Fragment {

    private static final int PICK_VIDEO_REQUEST = 1;
    private static final int RESULT_CANCELED = 0;

    private VideoView videoView;
    private Button chooseBn, uploadBn;
    private Uri filePath;
    private EditText description;
    private StorageReference storageReference;
    private FirebaseDatabase db;
    private DatabaseReference dref;
    ProgressBar progressBar;


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
         progressBar = view.findViewById(R.id.progressBar);

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
                 uploadFile();
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
            filePath = data.getData();
            videoView.setVideoURI(filePath);
            videoView.start();
        }
    }

    private void uploadFile(){
        final String video_description = description.getText().toString().trim();
        if (filePath == null){
            Toast.makeText(getActivity(), "Video is mandatory", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(video_description)) {
            Toast.makeText(getActivity(), "Enter some description!", Toast.LENGTH_SHORT).show();
        }
        else {
            progressBar.setVisibility(View.VISIBLE);
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

            final StorageReference myRef;
            final String videoID = UUID.randomUUID().toString();
            myRef = storageReference.child("feedVideos/" + videoID);
            String videoUrl = myRef.getDownloadUrl().toString();

            myRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getActivity(), "File Uploaded ", Toast.LENGTH_LONG).show();
                        }

                    });
        }
    }

}
