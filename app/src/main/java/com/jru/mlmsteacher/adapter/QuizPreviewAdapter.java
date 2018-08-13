package com.jru.mlmsteacher.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jru.mlmsteacher.R;
import com.jru.mlmsteacher.api.model.QuizPreviewItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuizPreviewAdapter extends RecyclerView.Adapter<QuizPreviewAdapter.ViewHolder> {

    List<QuizPreviewItem> list;

    public QuizPreviewAdapter(List<QuizPreviewItem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_quiz_preview, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        QuizPreviewItem item = list.get(position);

        holder.tvTitle.setText(item.getTitle());
        holder.tvTimeLimit.setText(item.getTimeLimit() + " minutes");
        holder.tvCreated.setText("Quiz date: " + item.getQuizDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvTimeLimit)
        TextView tvTimeLimit;
        @BindView(R.id.tvCreated)
        TextView tvCreated;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
