package com.example.android.gharkikheti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.android.gharkikheti.upload_need;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragmentRecyclerAdapter extends  RecyclerView.Adapter<ProfileFragmentRecyclerAdapter.ViewHolder>{

    private Context context;
    private List<upload_need> muploads;
    public ProfileFragmentRecyclerAdapter(Context context,List<upload_need> muploads){
        this.context=context;
        this.muploads=muploads;
    }

    @NonNull
    @Override
    public ProfileFragmentRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_crop_recycler_items,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileFragmentRecyclerAdapter.ViewHolder holder, int position) {
        upload_need uploadCurrent=new upload_need();
 uploadCurrent=muploads.get(position);
      holder.Crop_name.setText(uploadCurrent.getmName());
      holder.start_date.setText(uploadCurrent.getMstartdate());
      holder.end_date.setText(uploadCurrent.getMenddate());
      Glide.with(context.getApplicationContext())
              .load(uploadCurrent.getmImageUrl())
              .into(holder.crop_image)
      ;

    }

    @Override
    public int getItemCount() {
        return muploads.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView crop_image;
        TextView Crop_name,start_date,end_date;
        RelativeLayout parent_layout_crop;
        public ViewHolder(View itemview){
            super(itemview);
            crop_image=itemview.findViewById(R.id.crop_image);
            Crop_name=itemview.findViewById(R.id.Crop_name);
            start_date=itemview.findViewById(R.id.start_date);
            end_date=itemview.findViewById(R.id.end_date);
            parent_layout_crop=itemview.findViewById(R.id.parent_layout_crop);
        }
    }
}
