package jeuCalcul;


import java.util.ArrayList;
import java.util.List;

public class Jeu {

    private List<Question> questions = new ArrayList<>();
    private int numberCorrect;
    private int numberIncorrect;
    private int totalQuestions;
    private int score;
    private Question currentQuestion;

    public Jeu() {
        numberCorrect = 0;
        numberIncorrect = 0;
        totalQuestions = 0;
        score = 0;
        currentQuestion = new Question(10);
    }
    public void makeNewQuestion() {
        currentQuestion = new Question(totalQuestions * 2 + 5);
        totalQuestions++;
        questions.add(currentQuestion);
    }

    public boolean checkAnswer(int submittedAnswer) {
        boolean isCorrect;
        if (currentQuestion.getAnswer() == submittedAnswer) {
            numberCorrect++;
            isCorrect = true;
        } else {
            numberIncorrect++;
            isCorrect=false;
        }
        score = numberCorrect * 10 - numberIncorrect * 30;
        return isCorrect;
    }
    public int getNumberCorrect() {
        return numberCorrect;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public int getScore() {
        return score;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }
}
