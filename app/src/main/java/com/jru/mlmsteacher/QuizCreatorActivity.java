package com.jru.mlmsteacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.jru.mlmsteacher.adapter.ACTAdapter;
import com.jru.mlmsteacher.adapter.QuizQuestionAdapter;
import com.jru.mlmsteacher.data.Keys;
import com.jru.mlmsteacher.model.QuizQuestion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuizCreatorActivity extends AppCompatActivity {

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

    ArrayList<QuizQuestion> questions;
    List<String> timeLimitList;
    int timeLimit = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_creator);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        testData();
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

        questions = new ArrayList<>();
        adapter = new QuizQuestionAdapter(questions);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

    }

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
                startActivityForResult(new Intent(this, QuestionCreatorActivity.class).putExtra("id", questions.size()), 1000);
                break;
            case R.id.btnPreview:
                Bundle extras = new Bundle();
                extras.putParcelableArrayList(Keys.QUIZ_QUESTIONS, questions);
                startActivity(new Intent(this, QuizPreviewActivity.class).putExtras(extras));
                break;
            case R.id.btnGenerate:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ( item.getItemId() ){
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void onAddClicked() {
        String question = etQuestion.getText().toString();
        String optionA = etOptionA.getText().toString();
        String optionB = etOptionB.getText().toString();
        String optionC = etOptionC.getText().toString();
        String optionD = etOptionD.getText().toString();
        int answer = Integer.parseInt(etCorrectAnswer.getText().toString());
        int timeLimit = Integer.parseInt(etTimeLimit.getText().toString());

        if (requestCode == 1000 && resultCode == RESULT_OK) {
            if (data.getExtras() != null) {
                QuizQuestion question = data.getParcelableExtra("Question");
                // TODO : Check if ID exist;
                questions.add(question);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
