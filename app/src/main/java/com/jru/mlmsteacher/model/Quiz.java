package com.jru.mlmsteacher.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Quiz implements Parcelable{

    private String title;
    private int numOfQuestion;
    private int timeLimit;
    public ArrayList<QuizQuestion> questions;

    public Quiz(String title, int numOfQuestion, int timeLimit, ArrayList<QuizQuestion> questions) {
        this.title = title;
        this.numOfQuestion = numOfQuestion;
        this.timeLimit = timeLimit;
        this.questions = questions;
    }

    protected Quiz(Parcel in) {
        title = in.readString();
        numOfQuestion = in.readInt();
        questions = in.createTypedArrayList(QuizQuestion.CREATOR);
    }

    public static final Creator<Quiz> CREATOR = new Creator<Quiz>() {
        @Override
        public Quiz createFromParcel(Parcel in) {
            return new Quiz(in);
        }

        @Override
        public Quiz[] newArray(int size) {
            return new Quiz[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public int getNumOfQuestion() {
        return numOfQuestion;
    }

    public ArrayList<QuizQuestion> getQuestions() {
        return questions;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(numOfQuestion);
        dest.writeInt(timeLimit);
        dest.writeTypedList(questions);
    }
}
