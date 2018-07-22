package com.jru.mlmsteacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jru.mlmsteacher.data.DummyData;
import com.jru.mlmsteacher.model.Quiz;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Quiz quiz = DummyData.getQuiz();

        Bundle extras = new Bundle();
        extras.putParcelableArrayList("Questions",  quiz.getQuestions());
//        extras.putParcelableArrayList("Questions", quiz.getQuestions());
//        extras.putParcelable("QUIZ", quiz);
        Intent intent = new Intent(this, Test2Activity.class).putExtras(extras);
        startActivity(intent);
    }
}
