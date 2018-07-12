package com.jru.mlmsteacher.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Quiz implements Parcelable {

    private String title;
    List<QuizQuestion> questions;

    public Quiz(String title, List<QuizQuestion> questions) {
        this.title = title;
        this.questions = questions;
    }

    protected Quiz(Parcel in) {
        title = in.readString();
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

    public List<QuizQuestion> getQuestions() {
        return questions;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
    }
}
