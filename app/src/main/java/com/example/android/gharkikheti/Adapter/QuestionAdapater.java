package com.example.android.gharkikheti.Adapter;

import android.app.Activity;
import android.content.Context;
import android.text.style.QuoteSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.gharkikheti.Modal.QuestionModel;
import com.example.android.gharkikheti.R;

import java.util.List;

public class QuestionAdapater extends RecyclerView.Adapter<QuestionAdapater.QuestionViewHolder> {
    List<QuestionModel> list;
    Context context;
    NavController navController;
    Activity activity;

    public QuestionAdapater(Context context, List<QuestionModel> list, Activity activity) {
        this.context = context;
        this.list = list;
        this.activity = activity;
        navController = Navigation.findNavController(activity, R.id.my_nav_host_fragment);
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
        holder.postedBy.setText("Asked by: " + questionModel.getPostedBy());
        holder.timestamp.setText(questionModel.getTimestamp());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_landingFragment_to_answerFragment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder{
        TextView question, postedBy, timestamp;
        LinearLayout layout;
        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.question);
            postedBy = itemView.findViewById(R.id.question_postedBy);
            timestamp = itemView.findViewById(R.id.question_timestamp);
            layout = itemView.findViewById(R.id.question_layout);
        }
    }
}
