package com.jru.mlmsteacher.model;

import android.os.Parcel;
import android.os.Parcelable;

public class QuizQuestion implements Parcelable {

    private int questionNumber;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private int answer;
    private long timeLimit; // in millis;

    public QuizQuestion(int questionNumber, String question, String optionA, String optionB,
                        String optionC, String optionD, int answer, long timeLimit) {
        this.questionNumber = questionNumber;
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answer = answer;
        this.timeLimit = timeLimit;
    }

    protected QuizQuestion(Parcel in) {
        questionNumber = in.readInt();
        question = in.readString();
        optionA = in.readString();
        optionB = in.readString();
        optionC = in.readString();
        optionD = in.readString();
        answer = in.readInt();
        timeLimit = in.readLong();
    }

    public static final Creator<QuizQuestion> CREATOR = new Creator<QuizQuestion>() {
        @Override
        public QuizQuestion createFromParcel(Parcel in) {
            return new QuizQuestion(in);
        }

        @Override
        public QuizQuestion[] newArray(int size) {
            return new QuizQuestion[size];
        }
    };

    public int getQuestionNumber() {
        return questionNumber;
    }

    public String getQuestion() {
        return question;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public int getAnswer() {
        return answer;
    }

    public long getTimeLimit() {
        return timeLimit;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(questionNumber);
        dest.writeString(question);
        dest.writeString(optionA);
        dest.writeString(optionB);
        dest.writeString(optionC);
        dest.writeString(optionD);
        dest.writeInt(answer);
        dest.writeLong(timeLimit);
    }
}
