package com.jru.mlmsteacher.components.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.jru.mlmsteacher.R;
import com.jru.mlmsteacher.adapter.QuizPreviewAdapter;
import com.jru.mlmsteacher.api.calls.QuizPreview;
import com.jru.mlmsteacher.api.model.QuizPreviewItem;
import com.jru.mlmsteacher.api.response.QuizPreviewResponse;
import com.jru.mlmsteacher.components.BaseActivity;
import com.jru.mlmsteacher.data.EZSharedPreferences;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuizActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.fabAdd)
    FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        QuizPreview.request(EZSharedPreferences.getAccessToken(this), new QuizPreview.RequestListener() {
            @Override
            public void isSuccessful(QuizPreviewResponse response) {
                populateData(response.getData());
            }

            @Override
            public void onFailure(String errorMessage) {

            }

            @Override
            public void showLoadingDialog() {
                showProgressDialog("Getting quizzes...");
            }

            @Override
            public void hideLoadingDialog() {
                hideProgressDialog();
            }
        });

    }

    private void populateData(List<QuizPreviewItem> data) {

        QuizPreviewAdapter adapter = new QuizPreviewAdapter(data);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    @OnClick(R.id.fabAdd)
    public void onViewClicked() {

        startActivity(new Intent(this, QuizCreatorActivity.class));

    }
}
