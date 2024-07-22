package org.istqbinsider.ui;

import org.istqbinsider.model.Question;

public interface UI {
    void showWelcomeScreen();
    void displayQuestion(Question question);
    String getUserInput();
    void showCorrectAnswerFeedback();
    void showGameOverScreen(int score, int streak);
    void showTimeUpScreen(int score, int streak);
    void showGameCompletedScreen(int score, int streak);
    void showErrorMessage(String message);
}