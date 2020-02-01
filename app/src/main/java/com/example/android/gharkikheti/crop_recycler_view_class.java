package com.example.android.gharkikheti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class crop_recycler_view_class extends  RecyclerView.Adapter<crop_recycler_view_class.ViewHolder>{
   private ArrayList<String> mcrop_image=new ArrayList<>();
    private ArrayList<String> mcrop_name=new ArrayList<>();
    private ArrayList<String> mstart_date=new ArrayList<>();
    private ArrayList<String> mend_date=new ArrayList<>();
    private Context context;

    public crop_recycler_view_class(Context context,ArrayList<String> mcrop_image, ArrayList<String> mcrop_name, ArrayList<String> mstart_date, ArrayList<String> mend_date) {
        this.mcrop_image = mcrop_image;
        this.mcrop_name = mcrop_name;
        this.mstart_date = mstart_date;
        this.mend_date = mend_date;
        this.context = context;
    }

    @NonNull
    @Override
    public crop_recycler_view_class.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_crop_recycler_items,parent,false);
    ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull crop_recycler_view_class.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
