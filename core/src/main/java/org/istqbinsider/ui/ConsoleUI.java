package org.istqbinsider.ui;

import org.istqbinsider.model.Question;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI implements UI {
    private Scanner scanner;

    public ConsoleUI() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void showWelcomeScreen() {
        System.out.println("Welcome to the MCQ Application!");
        System.out.println("Get ready for Survival Mode!");
    }

    @Override
    public void displayQuestion(Question question) {
        System.out.println("\n" + question.getQuestionText());
        List<String> options = question.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    @Override
    public String getUserInput() {
        return scanner.nextLine();
    }

    @Override
    public void showCorrectAnswerFeedback() {
        System.out.println("Correct! Keep going!");
    }

    @Override
    public void showGameOverScreen(int score, int streak) {
        System.out.println("Game Over!");
        System.out.println("Your score: " + score);
        System.out.println("Your longest streak: " + streak);
    }

    @Override
    public void showTimeUpScreen(int score, int streak) {
        System.out.println("Time's up!");
        System.out.println("Your score: " + score);
        System.out.println("Your longest streak: " + streak);
    }

    @Override
    public void showGameCompletedScreen(int score, int streak) {
        System.out.println("Congratulations! You've completed all questions!");
        System.out.println("Your final score: " + score);
        System.out.println("Your longest streak: " + streak);
    }
    @Override
    public void showErrorMessage(String message) {
        System.err.println("Error: " + message);
    }
}