package com.jru.mlmsteacher.model;

import java.util.ArrayList;

public class Quiz {

    private String title;
    private int numOfQuestion;
    public ArrayList<QuizQuestion> questions;

    public Quiz(String title, int numOfQuestion, ArrayList<QuizQuestion> questions) {
        this.title = title;
        this.numOfQuestion = numOfQuestion;
        this.questions = questions;
    }

    public String getTitle() {
        return title;
    }

    public int getNumOfQuestion() {
        return numOfQuestion;
    }

    public ArrayList<QuizQuestion> getQuestions() {
        return questions;
    }
}
