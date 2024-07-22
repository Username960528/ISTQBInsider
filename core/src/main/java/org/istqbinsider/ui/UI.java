package org.istqbinsider.ui;
import java.util.List;

import org.istqbinsider.model.Question;

public class UI {
    public void showWelcomeScreen() {
        System.out.println("Welcome to the MCQ Application!");
        System.out.println("Get ready for Survival Mode!");
    }

    public void displayQuestion(Question question) {
        System.out.println("\n" + question.getQuestionText());
        List<String> options = question.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    public String getUserInput() {
        // In a real application, we would implement proper input handling here
        return ""; // Placeholder
    }

    public void showCorrectAnswerFeedback() {
        System.out.println("Correct! Keep going!");
    }

    public void showGameOverScreen(int score, int streak) {
        System.out.println("Game Over!");
        System.out.println("Your score: " + score);
        System.out.println("Your longest streak: " + streak);
    }

    public void showTimeUpScreen(int score, int streak) {
        System.out.println("Time's up!");
        System.out.println("Your score: " + score);
        System.out.println("Your longest streak: " + streak);
    }

    public void showGameCompletedScreen(int score, int streak) {
        System.out.println("Congratulations! You've completed all questions!");
        System.out.println("Your final score: " + score);
        System.out.println("Your longest streak: " + streak);
    }
}