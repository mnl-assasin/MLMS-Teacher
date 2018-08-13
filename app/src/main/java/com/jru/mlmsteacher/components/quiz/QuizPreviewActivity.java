package com.jru.mlmsteacher.components.quiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.jru.mlmsteacher.R;
import com.jru.mlmsteacher.adapter.QuizQuestionAdapter;
import com.jru.mlmsteacher.data.Keys;
import com.jru.mlmsteacher.model.QuizQuestion;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuizPreviewActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv)
    RecyclerView rv;

    QuizQuestionAdapter adapter;
    List<QuizQuestion> questions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_preview);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        if (getIntent().getExtras() != null) {
            questions = getIntent().getExtras()
                    .getParcelableArrayList(Keys.QUIZ_QUESTIONS);

            adapter = new QuizQuestionAdapter(questions);
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(adapter);

        }
    }
}
