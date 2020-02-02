package com.example.android.gharkikheti.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.gharkikheti.Modal.RightsAndSchemeModel;
import com.example.android.gharkikheti.R;

public class RightsAndSchemeAdapter extends RecyclerView.Adapter<RightsAndSchemeAdapter.RightsAnsSchemeViewHolder>{

    List<RightsAndSchemeModel> list;

    public RightsAndSchemeAdapter(List<RightsAndSchemeModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RightsAnsSchemeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_rights, parent, false);
        RightsAnsSchemeViewHolder viewholder = new RightsAnsSchemeViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull RightsAnsSchemeViewHolder holder, int position) {
        RightsAndSchemeModel rightsAndSchemeModel = list.get(position);
        holder.heading.setText(rightsAndSchemeModel.getHeading());
        holder.description.setText(rightsAndSchemeModel.getDescription());
        holder.image.setImageDrawable(rightsAndSchemeModel.getImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class RightsAnsSchemeViewHolder extends RecyclerView.ViewHolder{
        TextView heading, description;
        ImageView image;
        public RightsAnsSchemeViewHolder(@NonNull View itemView) {
            super(itemView);
            heading = itemView.findViewById(R.id.rights_heading);
            description = itemView.findViewById(R.id.rights_description);
            image = itemView.findViewById(R.id.rights_image);
        }
    }
}
