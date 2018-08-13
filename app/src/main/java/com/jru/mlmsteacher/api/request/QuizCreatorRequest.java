package com.jru.mlmsteacher.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jru.mlmsteacher.api.model.Question;

import java.util.List;

public class QuizCreatorRequest {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("time_limit")
    @Expose
    private int timeLimit;
    @SerializedName("questions")
    @Expose
    private List<Question> questions = null;

    public QuizCreatorRequest(String title, Integer timeLimit, List<Question> questions) {
        this.title = title;
        this.timeLimit = timeLimit;
        this.questions = questions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }


    public String toString() {

        String listOfQuestion = "";

        for (int i = 0; i < questions.size(); i++) {
            listOfQuestion += "Question: " + questions.get(i).toString() + "\n";
        }
        return "title: " + title +
                "\ntime_limit: " + timeLimit +
                "\nquestions: " + listOfQuestion;



    }
}