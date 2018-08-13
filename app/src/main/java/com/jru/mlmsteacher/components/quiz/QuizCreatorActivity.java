package com.jru.mlmsteacher.components.quiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jru.mlmsteacher.R;
import com.jru.mlmsteacher.adapter.ACTAdapter;
import com.jru.mlmsteacher.adapter.QuizQuestionAdapter;
import com.jru.mlmsteacher.api.calls.CreateQuiz;
import com.jru.mlmsteacher.api.model.Question;
import com.jru.mlmsteacher.api.request.QuizCreatorRequest;
import com.jru.mlmsteacher.api.response.QuizCreatorResponse;
import com.jru.mlmsteacher.components.BaseActivity;
import com.jru.mlmsteacher.data.EZSharedPreferences;
import com.jru.mlmsteacher.data.Keys;
import com.jru.mlmsteacher.model.QuizQuestion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuizCreatorActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.etTitle)
    EditText etTitle;
    @BindView(R.id.actTimeLimit)
    AutoCompleteTextView actTimeLimit;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.btnAdd)
    Button btnAdd;
    @BindView(R.id.btnPreview)
    Button btnPreview;
    @BindView(R.id.btnGenerate)
    Button btnGenerate;

    QuizQuestionAdapter adapter;

    ArrayList<QuizQuestion> quizQuestions;
    List<String> timeLimitList;
    int timeLimit = 0;


    List<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_creator);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initData();
        initQuestionPreview();
        initListener();

    }

    private void initData() {

        timeLimitList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.time_limit)));
        ACTAdapter adapter = new ACTAdapter(this, R.layout.item_act, timeLimitList);
        actTimeLimit.setAdapter(adapter);

    }


    private void initQuestionPreview() {

        quizQuestions = new ArrayList<>();
        questions = new ArrayList<>();
        adapter = new QuizQuestionAdapter(quizQuestions);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

    }

    @SuppressLint("ClickableViewAccessibility")
    private void initListener() {

        actTimeLimit.setKeyListener(null);
        actTimeLimit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO: Add custom timeLimit;
                timeLimit = (position + 1) * 5;
            }
        });

        actTimeLimit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ((AutoCompleteTextView) v).showDropDown();
                return false;
            }
        });
    }

    @OnClick({R.id.btnAdd, R.id.btnPreview, R.id.btnGenerate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                startActivityForResult(new Intent(this, QuestionCreatorActivity.class).putExtra("id", quizQuestions.size()), 1000);
                break;
            case R.id.btnPreview:
                Bundle extras = new Bundle();
                extras.putParcelableArrayList(Keys.QUIZ_QUESTIONS, quizQuestions);
                startActivity(new Intent(this, QuizPreviewActivity.class).putExtras(extras));
                break;
            case R.id.btnGenerate:
                onGenerateClicked();
                break;
        }
    }

    private void onGenerateClicked() {

        String title = etTitle.getText().toString();
        int timeLimit = this.timeLimit;

        if (!title.equals("") && timeLimit != 0 && questions != null) {

            final QuizCreatorRequest request = new QuizCreatorRequest(title, timeLimit, questions);

            CreateQuiz.request(EZSharedPreferences.getAccessToken(this), request, new CreateQuiz.RequestListener() {
                @Override
                public void isSuccessful(QuizCreatorResponse response) {
                    Log.d("TAG_", "Response: " + response.getMessage());
                    Toast.makeText(getApplicationContext(), "Quiz created successfuly!", Toast.LENGTH_LONG).show();
                    finish();
                }

                @Override
                public void onFailure(String errorMessage) {
                    Toast.makeText(getApplicationContext(), "Failure: " + errorMessage, Toast.LENGTH_LONG).show();
                    Log.d("TAG_", "errorMessage: " + errorMessage);
                }

                @Override
                public void showLoadingDialog() {
                    showProgressDialog("Creating quiz please wait...");
                }

                @Override
                public void hideLoadingDialog() {
                    hideProgressDialog();
                }
            });

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                quizQuestions.add((QuizQuestion) extras.getParcelable("QuizQuestion"));
                questions.add((Question) extras.getParcelable("Question"));

                adapter.notifyDataSetChanged();
            }
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
