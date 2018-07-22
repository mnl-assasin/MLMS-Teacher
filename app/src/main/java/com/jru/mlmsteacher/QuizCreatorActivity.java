package com.jru.mlmsteacher;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jru.mlmsteacher.data.Keys;
import com.jru.mlmsteacher.model.Quiz;
import com.jru.mlmsteacher.model.QuizQuestion;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuizCreatorActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.etTitle)
    TextInputEditText etTitle;
    @BindView(R.id.tilTitle)
    TextInputLayout tilTitle;
    @BindView(R.id.tvItems)
    TextView tvItems;
    @BindView(R.id.etQuestion)
    TextInputEditText etQuestion;
    @BindView(R.id.tilQuestion)
    TextInputLayout tilQuestion;
    @BindView(R.id.etOptionA)
    TextInputEditText etOptionA;
    @BindView(R.id.tilOptionA)
    TextInputLayout tilOptionA;
    @BindView(R.id.etOptionB)
    TextInputEditText etOptionB;
    @BindView(R.id.tilOptionB)
    TextInputLayout tilOptionB;
    @BindView(R.id.etOptionC)
    TextInputEditText etOptionC;
    @BindView(R.id.tilOptionC)
    TextInputLayout tilOptionC;
    @BindView(R.id.etOptionD)
    TextInputEditText etOptionD;
    @BindView(R.id.tilOptionD)
    TextInputLayout tilOptionD;
    @BindView(R.id.etCorrectAnswer)
    TextInputEditText etCorrectAnswer;
    @BindView(R.id.tilCorrectAnswer)
    TextInputLayout tilCorrectAnswer;
    @BindView(R.id.etTimeLimit)
    TextInputEditText etTimeLimit;
    @BindView(R.id.tilTimeLimit)
    TextInputLayout tilTimeLimit;
    @BindView(R.id.btnAdd)
    Button btnAdd;
    @BindView(R.id.btnPreview)
    Button btnPreview;
    @BindView(R.id.btnGenerate)
    Button btnGenerate;

    Quiz quiz;
    List<QuizQuestion> questions;

    int questionIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_creator);
        ButterKnife.bind(this);

        testData();
        initData();
        initListener();
    }

    private void testData() {

        etQuestion.setText("Question " + questionIndex + 1);
        etOptionA.setText("OptionA for question: " + questionIndex + 1);
        etOptionB.setText("OptionB for question: " + questionIndex + 1);
        etOptionC.setText("OptionC for question: " + questionIndex + 1);
        etOptionD.setText("OptionD for question: " + questionIndex + 1);
        etCorrectAnswer.setText("1");
        etTimeLimit.setText("1000");


    }

    private void initData() {


        questions = new ArrayList<>();
        questionIndex = 0;

    }

    @OnClick({R.id.btnAdd, R.id.btnPreview, R.id.btnGenerate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                onAddClicked();
                break;
            case R.id.btnPreview:
                Bundle extras = new Bundle();
                extras.putParcelableArrayList(Keys.QUIZ_QUESTIONS, (ArrayList<? extends Parcelable>) questions);
                startActivity(new Intent(this, QuizPreviewActivity.class).putExtras(extras));
                break;
            case R.id.btnGenerate:
                break;
        }
    }

    private void onAddClicked() {
        String question = etQuestion.getText().toString();
        String optionA = etOptionA.getText().toString();
        String optionB = etOptionB.getText().toString();
        String optionC = etOptionC.getText().toString();
        String optionD = etOptionD.getText().toString();
        int answer = Integer.parseInt(etCorrectAnswer.getText().toString());
        int timeLimit = Integer.parseInt(etTimeLimit.getText().toString());

        // TODO: Add validation

        questions.add(new QuizQuestion(questionIndex, question, optionA, optionB, optionC, optionD, answer, timeLimit));


        updateUI();


    }

    private void updateUI() {

//        // TODO: uncomment re-initilization of values;
//        etQuestion.setText("");
//        etOptionA.setText("");
//        etOptionB.setText("");
//        etOptionC.setText("");
//        etOptionD.setText("");
//
//        // TODO: I know this will change to AutoCompleteTextView;
//        etCorrectAnswer.setText("");
//        etTimeLimit.setText("");

        testData();
        questionIndex++;
        tvItems.setText(String.valueOf(questionIndex));


    }

    private void initListener() {

        // TODO: Add function for textInputLayouts;

    }
}
