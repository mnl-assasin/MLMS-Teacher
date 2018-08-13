package com.jru.mlmsteacher.components.quiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.jru.mlmsteacher.R;
import com.jru.mlmsteacher.adapter.ACTAdapter;
import com.jru.mlmsteacher.api.model.Choice;
import com.jru.mlmsteacher.api.model.Question;
import com.jru.mlmsteacher.model.QuizQuestion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuestionCreatorActivity extends AppCompatActivity {

    String TAG = "TAG_QuestionCreatorActivity";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.etQuestion)
    EditText etQuestion;
    @BindView(R.id.etOptionA)
    EditText etOptionA;
    @BindView(R.id.etOptionB)
    EditText etOptionB;
    @BindView(R.id.etOptionC)
    EditText etOptionC;
    @BindView(R.id.etOptionD)
    EditText etOptionD;
    @BindView(R.id.actCorrectAnswer)
    AutoCompleteTextView actCorrectAnswer;
    @BindView(R.id.btnAdd)
    Button btnAdd;

    List<String> options;

    int questionId = 0;
    int correctAnswer = 0;

    String[] answer = {"a", "b", "c", "d"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ButterKnife.bind(this);

        initData();
        initListener();
    }

    private void initData() {

        options = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.options)));
        ACTAdapter adapter = new ACTAdapter(this, R.layout.item_act, options);
        actCorrectAnswer.setAdapter(adapter);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            questionId = extras.getInt("id");
            Log.d(TAG, "questionId: " + questionId);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initListener() {

        actCorrectAnswer.setKeyListener(null);
        actCorrectAnswer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                correctAnswer = position;
            }
        });

        actCorrectAnswer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ((AutoCompleteTextView) v).showDropDown();
                return false;
            }
        });
    }


    @OnClick(R.id.btnAdd)
    public void onViewClicked() {

        String question = etQuestion.getText().toString();
        String optionA = etOptionA.getText().toString().trim();
        String optionB = etOptionB.getText().toString().trim();
        String optionC = etOptionB.getText().toString().trim();
        String optionD = etOptionB.getText().toString().trim();

        List<Choice> choices = new ArrayList<>();
        choices.add(new Choice(optionA, optionB, optionC, optionD));


        // TODO: Add field validation;
        Intent intent = getIntent();
        Bundle extras = new Bundle();
        extras.putParcelable("QuizQuestion", new QuizQuestion(questionId, question, optionA, optionB, optionC, optionD, correctAnswer));
        extras.putParcelable("Question", new Question(question, answer[correctAnswer], choices));
        intent.putExtras(extras);

        setResult(RESULT_OK, intent);
        finish();
    }
}
