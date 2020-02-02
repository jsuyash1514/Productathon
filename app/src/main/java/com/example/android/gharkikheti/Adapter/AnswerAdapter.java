package com.example.android.gharkikheti.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.gharkikheti.Modal.AnswerModel;
import com.example.android.gharkikheti.R;

import java.util.List;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder> {
    List<AnswerModel> list;

    public AnswerAdapter(List<AnswerModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public AnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_answer, parent, false);
        AnswerViewHolder answerViewHolder = new AnswerViewHolder(view);
        return answerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerViewHolder holder, int position) {
        AnswerModel answerModel = list.get(position);
        holder.name.setText(answerModel.getName());
        holder.timestamp.setText(answerModel.getTimestamp());
        holder.answer.setText(answerModel.getAnswer());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class AnswerViewHolder extends RecyclerView.ViewHolder{
        TextView name, timestamp, answer;
        public AnswerViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.answer_name);
            timestamp = itemView.findViewById(R.id.answer_timestamp);
            answer = itemView.findViewById(R.id.answer);
        }
    }
}
