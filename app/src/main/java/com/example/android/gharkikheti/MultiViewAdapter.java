package com.example.android.gharkikheti;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MultiViewAdapter {
    private ArrayList<Feed> dataSet;
    Context mContext;
    int total_types;
    MediaPlayer mPlayer;


    public static class TextTypeViewHolder extends RecyclerView.ViewHolder {

        TextView txtType;
        CardView cardView;

        public TextTypeViewHolder(View itemView) {
            super(itemView);

            this.txtType =  itemView.findViewById(R.id.type);
            this.cardView = itemView.findViewById(R.id.card_view);
        }
    }

    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {

        TextView txtType;
        ImageView image;

        public ImageTypeViewHolder(View itemView) {
            super(itemView);

            this.txtType =  itemView.findViewById(R.id.type);
            this.image = itemView.findViewById(R.id.background);
        }
    }

    public static class VideoTypeViewHolder extends RecyclerView.ViewHolder {

        TextView txtType;
        VideoView video;
        //FloatingActionButton fab;

        public VideoTypeViewHolder(View itemView) {
            super(itemView);

            this.txtType = itemView.findViewById(R.id.type);
            this.video = itemView.findViewById(R.id.video_view);
        }
    }

    public MultiViewAdapter(ArrayList<Feed>data, Context context) {
        this.dataSet = data;
        this.mContext = context;
        total_types = dataSet.size();
    }

//    //@Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//        View view;
//        switch (viewType) {
//            case Feed.TEXT_TYPE:
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_type, parent, false);
//                return new TextTypeViewHolder(view);
//            case Feed.IMAGE_TYPE:
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_type, parent, false);
//                return new ImageTypeViewHolder(view);
//            case Feed.VIDEO_TYPE:
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_type, parent, false);
//                return new VideoTypeViewHolder(view);
//        }
//        return null;
//    }
//
//   // @Override
//    public int getItemViewType(int position) {
//
//        switch (dataSet.get(position).type) {
//            case 0:
//                return Feed.TEXT_TYPE;
//            case 1:
//                return Feed.IMAGE_TYPE;
//            case 2:
//                return Feed.VIDEO_TYPE;
//            default:
//                return -1;
//        }
//    }
//
//   // @Override
//    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {
//
//        Feed object = dataSet.get(listPosition);
//        if (object != null) {
//            switch (object.type) {
//
//                case Feed.TEXT_TYPE:
//                    ((TextTypeViewHolder) holder).txtType.setText(object.text);
//                    break;
//
//                case Feed.IMAGE_TYPE:
//                    ((ImageTypeViewHolder) holder).txtType.setText(object.text);
//                    ((ImageTypeViewHolder) holder).image.setImageResource(object.data);
//                    break;
//
//                case Feed.VIDEO_TYPE:
//
//                    ((VideoTypeViewHolder) holder).txtType.setText(object.text);
//
//                    ((VideoTypeViewHolder) holder).video.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//
//
//                            }
//
//                    });
//                    break;
//            }
//        }
//    }

    //@Override
    public int getItemCount() {
        return dataSet.size();
    }
}
