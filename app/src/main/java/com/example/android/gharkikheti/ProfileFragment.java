package com.example.android.gharkikheti;

import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
     private int t=0,u=0;
private ArrayList<String> mcropnames=new ArrayList<>();
    private ArrayList<String> mstartdate=new ArrayList<>();
    private ArrayList<String> menddate=new ArrayList<>();
    private ArrayList<String> mImageurls=new ArrayList<>();
    EditText mcrop_name_text;
    private String mYear, mMonth, mDay, mHour, mMinute;
    EditText mstart_date_text,txtdates;
    EditText mend_date_text,txtdatee;
    ProgressBar mProgressBar;
    Button mupload,mchoose;
    ImageView mImageView;
    String imageUrl;
    private Uri mImageUri;
    private DatabaseReference mDatabaseRef;
    private StorageReference mStorageRef;
    private StorageTask mUploadTask;
    private ProfileFragmentRecyclerAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private List<upload_need> mUploads;
    private static final int PICK_IMAGE_REQUEST = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        mcrop_name_text=view.findViewById(R.id.Crop_name_text);
        mstart_date_text=view.findViewById(R.id.start_date_text);
        txtdates=view.findViewById(R.id.start_date_text);
        txtdatee=view.findViewById(R.id.end_date_text);
        mstart_date_text=view.findViewById(R.id.start_date_text);
        mstart_date_text=view.findViewById(R.id.start_date_text);
        mend_date_text=view.findViewById(R.id.end_date_text);
        mupload=view.findViewById(R.id.upload_crop_details);
        mchoose=view.findViewById(R.id.choose_crop_details);
        mImageView=view.findViewById(R.id.image_upload_display);
        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");
        mchoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,PICK_IMAGE_REQUEST);
                t=0;u=0;
                txtdatee.setText(null);
                txtdates.setText(null);
            }
        });
        if(t==0) {t++;
            mstart_date_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Calendar c = Calendar.getInstance();
                    mYear = Integer.toString(c.get(Calendar.YEAR));
                    mMonth = Integer.toString(c.get(Calendar.MONTH));
                    mDay = Integer.toString(c.get(Calendar.DAY_OF_MONTH));
                    txtdates.setText((mDay) + "-" + (mMonth + 1) + "-" + (mYear));

                }
            });
        }
        mupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });
       mRecyclerView=view.findViewById(R.id.profile_crops_view_recycler);
       mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
       mUploads=new ArrayList<>();
       mDatabaseRef.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                   upload_need upload = postSnapshot.getValue(upload_need.class);
                   mUploads.add(upload);
               }
     mAdapter=new ProfileFragmentRecyclerAdapter(getActivity(),mUploads);
               mRecyclerView.setAdapter(mAdapter);

           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
            Picasso.get().load(mImageUri).into(mImageView);
        }
    }
    // TODO: Rename method, update argument and hook method into UI event

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void uploadFile() {
        if (mImageUri != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));

            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                }
                            }, 500);
                            if(taskSnapshot.getMetadata()!=null){

                                Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                                result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                         imageUrl = uri.toString();
                                        //createNewPost(imageUrl);

                                    }
                                });
                            upload_need upload = new upload_need(mcrop_name_text.getText().toString().trim(),
                                    mstart_date_text.getText().toString().trim(),mend_date_text.getText().toString().trim(),
                                    imageUrl
                            );
                            String uploadId = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(upload);

                        }}
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    })
                  ;

        } else {

        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
