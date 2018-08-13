package com.jru.mlmsteacher.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Question implements Parcelable {

    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("answer")
    @Expose
    private String answer;
    @SerializedName("choices")
    @Expose
    private List<Choice> choices = null;

    public Question(String question, String answer, List<Choice> choices) {
        this.question = question;
        this.answer = answer;
        this.choices = choices;
    }


    protected Question(Parcel in) {
        question = in.readString();
        answer = in.readString();
        choices = in.createTypedArrayList(Choice.CREATOR);
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    @Override
    public String toString() {

        String choice = "";

        for (int i = 0; i < choices.size(); i++) {
            choice += "Choices: " + choices.get(i).getA() + " " + choices.get(i).getB() + " " + choices.get(i).getC() + " " + choices.get(i).getD() + " ";
        }
        return "Question: " + question + "\nAnswer: " + answer + "\nChoices: " + choice;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(answer);
        dest.writeTypedList(choices);
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public List<Choice> getChoices() {
        return choices;
    }
}