package table4K.quiz;

import java.util.ArrayList;
import java.util.Random;

public class QuizQuestion {

    private int points;

    private String question;

    private ArrayList<String> answer;

    private String correctAnswer;

    public int getPoints() {
        return points;
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getAnswer() {
        return answer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }


    QuizQuestion(final int pointsEnter,
                 final String questionEnter,
                 final ArrayList<String> answerEnter,
                 final String correctAnswerEnter) {

        points = pointsEnter;
        question = questionEnter;
        answer = answerEnter;
        correctAnswer = correctAnswerEnter;
    }

    public ArrayList<String> getRandomAnswer() {
        ArrayList<String> temp = new ArrayList<>(getAnswer());
        ArrayList<String> randomAnswer = new ArrayList<>();

        for (int i = 0; i < answer.size(); i++) {
            int z = new Random().nextInt(temp.size());
            randomAnswer.add(temp.get(z));
            temp.remove(z);
        }

        return randomAnswer;
    }

    @Override
    public String toString() {
        String stringExit = "";

        stringExit += "Баллы = " + points + System.lineSeparator();
        stringExit += "Вопрос: " + question + System.lineSeparator();
        stringExit += "Варианты ответов: " + System.lineSeparator();
        for (String temp: answer) {
            stringExit += temp + System.lineSeparator();
        }
        stringExit += "Правильный ответ: " + correctAnswer;

        return stringExit;
    }

}
