// Path: /web/src/main/java/org/istqbinsider/web/WebUI.java

package org.istqbinsider.web;

import org.istqbinsider.ui.UI;
import org.istqbinsider.model.Question;

import java.util.List;

public class WebUI implements UI {
    @Override
    public void showWelcomeScreen() {
        // Implement web-specific welcome screen
        System.out.println("Welcome to the MCQ Application!");
        System.out.println("Get ready for Survival Mode!");
    }

    @Override
    public void displayQuestion(Question question) {
        // Implement web-specific question display
        System.out.println("\n" + question.getQuestionText());
        List<String> options = question.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    @Override
    public String getUserInput() {
        // Implement web-specific user input method
        return ""; // Placeholder
    }

    @Override
    public void showCorrectAnswerFeedback() {
        System.out.println("Correct! Keep going!");
    }

    @Override
    public void showGameOverScreen(int score, int streak) {
        // Implement web-specific game over screen
        System.out.println("Game Over!");
        System.out.println("Your score: " + score);
        System.out.println("Your longest streak: " + streak);
    }

    @Override
    public void showTimeUpScreen(int score, int streak) {
        // Implement web-specific time up screen
        System.out.println("Time's up!");
        System.out.println("Your score: " + score);
        System.out.println("Your longest streak: " + streak);
    }

    @Override
    public void showGameCompletedScreen(int score, int streak) {
        // Implement web-specific game completed screen
        System.out.println("Congratulations! You've completed all questions!");
        System.out.println("Your final score: " + score);
        System.out.println("Your longest streak: " + streak);
    }

    @Override
    public void showErrorMessage(String message) {
        // Implement web-specific error message display
        System.err.println("Error: " + message);
    }
}