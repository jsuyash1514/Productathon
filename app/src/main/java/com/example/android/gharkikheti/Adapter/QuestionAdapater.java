package com.example.android.gharkikheti.Adapter;

import android.text.style.QuoteSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.gharkikheti.Modal.QuestionModel;
import com.example.android.gharkikheti.R;

import java.util.List;

public class QuestionAdapater extends RecyclerView.Adapter<QuestionAdapater.QuestionViewHolder> {
    List<QuestionModel> list;

    public QuestionAdapater(List<QuestionModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_questions, parent, false);
        QuestionViewHolder questionViewHolder = new QuestionViewHolder(view);
        return questionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        final QuestionModel questionModel = list.get(position);
        holder.question.setText(questionModel.getQuestion());
        holder.postedBy.setText(questionModel.getPostedBy());
        holder.timestamp.setText(questionModel.getTimestamp());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder{
        TextView question, postedBy, timestamp;
        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.question);
            postedBy = itemView.findViewById(R.id.question_postedBy);
            timestamp = itemView.findViewById(R.id.question_timestamp);
        }
    }
}
