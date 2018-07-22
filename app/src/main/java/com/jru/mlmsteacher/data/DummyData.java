package com.jru.mlmsteacher.data;

import com.jru.mlmsteacher.model.Quiz;
import com.jru.mlmsteacher.model.QuizQuestion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DummyData {

    public static List<Quiz> getQuizzes() {
        List<Quiz> quizzes = new ArrayList<>();

        Random random = new Random();
        int x = random.nextInt(3) + 1;
        for (int i = 0; i < x; i++) {
            String title = "Quiz no. " + i;
            int numOfQuestions = random.nextInt(3) + 3;
            quizzes.add(new Quiz(title, numOfQuestions, generateQuestions(numOfQuestions)));
        }
        return quizzes;
    }

    public static Quiz getQuiz() {

        Random random = new Random();

        String title = "Generated Quiz";
        int numOfQuestions = random.nextInt(3) + 3;
        return new Quiz(title, numOfQuestions, generateQuestions(numOfQuestions));
    }

    private static ArrayList<QuizQuestion> generateQuestions(int numOfQuestion) {

        ArrayList<QuizQuestion> questions = new ArrayList<>();
        String option[] = new String[]{"Option A", "Option B", "Option C", "Option D"};

        String optionA;
        String optionB;
        String optionC;
        String optionD;

        Random random = new Random();
        for (int i = 0; i < numOfQuestion; i++) {

            int answerIndex = random.nextInt(4);

            switch (answerIndex) {
                case 0:
                    optionA = "This is the correct answer";
                    optionB = "This is not the answer";
                    optionC = "This is not the answer";
                    optionD = "This is not the answer";
                    break;
                case 1:
                    optionA = "This is not the answer";
                    optionB = "This is the correct answer";
                    optionC = "This is not the answer";
                    optionD = "This is not the answer";
                    break;
                case 2:
                    optionA = "This is not the answer";
                    optionB = "This is not the answer";
                    optionC = "This is the correct answer";
                    optionD = "This is not the answer";
                    break;
                default:
                    optionA = "This is not the answer";
                    optionB = "This is not the answer";
                    optionC = "This is not the answer";
                    optionD = "This is the correct answer";
                    break;


            }

            String question = "This is a generated question, for demo purposes only.\n\n" +
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer dictum faucibus augue quis iaculis. Quisque efficitur lacus dapibus sollicitudin tincidunt.\n\n" +
                    "The correct answer is " + option[answerIndex];

            int timeLimit = random.nextInt(5) + 1;
            questions.add(new QuizQuestion(i, question, optionA, optionB, optionC, optionD, answerIndex, timeLimit));
        }

        return questions;

    }
}
