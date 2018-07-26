package com.jru.mlmsteacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnAttendance)
    Button btnAttendance;
    @BindView(R.id.btnLesson)
    Button btnLesson;
    @BindView(R.id.btnQuiz)
    Button btnQuiz;
    @BindView(R.id.btnRecords)
    Button btnRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnAttendance, R.id.btnLesson, R.id.btnQuiz, R.id.btnRecords})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnAttendance:
                break;
            case R.id.btnLesson:
                break;
            case R.id.btnQuiz:
                startActivity(new Intent(this, QuizActivity.class));
                break;
            case R.id.btnRecords:
                break;
        }
    }
}
