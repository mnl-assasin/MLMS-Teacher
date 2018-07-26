package com.jru.mlmsteacher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jru.mlmsteacher.model.Quiz;
import com.jru.mlmsteacher.model.QuizQuestion;

import java.util.ArrayList;

public class Test2Activity extends AppCompatActivity {

    String TAG = "TAG_Test2Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        Quiz quiz = getIntent().getExtras().getParcelable("QUIZ");
        String title = quiz.getTitle();
        int numOfQuestion = quiz.getNumOfQuestion();

        Log.d(TAG, "Title: " + title);
        Log.d(TAG, "No. of items: " + numOfQuestion);
        ArrayList<QuizQuestion> questions = quiz.getQuestions();

//        ArrayList<QuizQuestion> questions = getIntent().getExtras().getParcelableArrayList("Questions");
        for (int i = 0; i < questions.size(); i++) {
            QuizQuestion question = questions.get(i);
            int questionId = question.getQuestionId();
            String quest = question.getQuestion();
            String optionA = question.getOptionA();
            String optionB = question.getOptionB();
            String optionC = question.getOptionC();
            String optionD = question.getOptionD();
            int answer = question.getAnswer();

            Log.d(TAG, "Question Id: " + questionId);
            Log.d(TAG, "Question: " + quest);
            Log.d(TAG, "OptionA " + optionA);
            Log.d(TAG, "OptionB " + optionB);
            Log.d(TAG, "OptionC " + optionC);
            Log.d(TAG, "OptionD " + optionD);
            Log.d(TAG, "Answer " + answer);

        }
    }
}
