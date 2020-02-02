package com.example.android.gharkikheti;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FeedAdapter extends FirebaseRecyclerAdapter<Feed, FeedAdapter.FeedViewHolder> {

    FeedAdapter(@NonNull FirebaseRecyclerOptions<Feed> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull FeedViewHolder holder, int position, @NonNull Feed feedItem) {

        //Picasso.get().load(productItem.getImage()).fit().into(holder.pImage);
        holder.mDescription.setText(feedItem.getVideo_description());
        holder.mTime.setText( feedItem.getTimestamp());
        holder.mVideo.setVideoPath(feedItem.getVideoUrl());


        //final String id = getSnapshots().getSnapshot(position).getId();

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                DatabaseReference ref =  FirebaseDatabase.getInstance().getReference();
//            }
//        });
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_type,
                parent, false);
        return new FeedViewHolder(v);
    }

    class FeedViewHolder extends RecyclerView.ViewHolder {
        private VideoView mVideo;
        private TextView mTime, mDescription;

        private FeedViewHolder(View itemView) {
            super(itemView);
            mVideo = itemView.findViewById(R.id.video_view);
            mDescription = itemView.findViewById(R.id.video_description);
            mTime = itemView.findViewById(R.id.timestamp);
        }
    }
}






