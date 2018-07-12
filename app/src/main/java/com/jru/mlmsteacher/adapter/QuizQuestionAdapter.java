package com.jru.mlmsteacher.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jru.mlmsteacher.R;
import com.jru.mlmsteacher.model.QuizQuestion;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuizQuestionAdapter extends RecyclerView.Adapter<QuizQuestionAdapter.ViewHolder> {

    List<QuizQuestion> list;

    public QuizQuestionAdapter(List<QuizQuestion> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_quiz_question, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        QuizQuestion question = list.get(position);

        holder.tvQuestionNumber.setText("Question #" + (question.getQuestionNumber() + 1));
        holder.tvQuestion.setText(question.getQuestion());

        holder.tvOptionA.setText(question.getOptionA());
        holder.tvOptionB.setText(question.getOptionB());
        holder.tvOptionC.setText(question.getOptionC());
        holder.tvOptionD.setText(question.getOptionD());

        // TODO: Add codes in util for corresponding correct values;
        holder.tvCorrectAnswer.setText("Correct answer: " + question.getAnswer());
        holder.tvTimeLimit.setText("Time limit: " + question.getTimeLimit());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvQuestionNumber)
        TextView tvQuestionNumber;
        @BindView(R.id.tvQuestion)
        TextView tvQuestion;
        @BindView(R.id.tvOptionA)
        TextView tvOptionA;
        @BindView(R.id.tvOptionB)
        TextView tvOptionB;
        @BindView(R.id.tvOptionC)
        TextView tvOptionC;
        @BindView(R.id.tvOptionD)
        TextView tvOptionD;
        @BindView(R.id.tvCorrectAnswer)
        TextView tvCorrectAnswer;
        @BindView(R.id.tvTimeLimit)
        TextView tvTimeLimit;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
