package com.jru.mlmsteacher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jru.mlmsteacher.api.calls.CreateQuiz;
import com.jru.mlmsteacher.api.model.Choice;
import com.jru.mlmsteacher.api.model.Question;
import com.jru.mlmsteacher.api.request.QuizCreatorRequest;
import com.jru.mlmsteacher.api.response.QuizCreatorResponse;
import com.jru.mlmsteacher.data.EZSharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        List<Question> questions = new ArrayList<>();
        List<Choice> choices = new ArrayList<>();
        choices.add(new Choice("String A", "String B", "String C", "String D"));
        Question question = new Question("Question 1", "Answer 1", choices);
        questions.add(question);
        QuizCreatorRequest request = new QuizCreatorRequest("QUIZ NO. 5", 60, questions);

        CreateQuiz.request(EZSharedPreferences.getAccessToken(this), request, new CreateQuiz.RequestListener() {
            @Override
            public void isSuccessful(QuizCreatorResponse response) {
                Log.d("TAG_", "isSuccessful");
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.d("TAG_", "onFailure" + errorMessage);
            }

            @Override
            public void showLoadingDialog() {
                Log.d("TAG_", "showLoadingDialog");
            }

            @Override
            public void hideLoadingDialog() {
                Log.d("TAG_", "hideLoadingDialog");
            }
        });
    }
}
