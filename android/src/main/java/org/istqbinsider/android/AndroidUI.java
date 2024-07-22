package org.istqbinsider.android;

import org.istqbinsider.ui.UI;
import org.istqbinsider.model.Question;

import android.app.Activity;
import android.widget.Toast;

public class AndroidUI implements UI {
    private Activity activity;

    public AndroidUI(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void showWelcomeScreen() {
        activity.runOnUiThread(() -> {
            Toast.makeText(activity, "Welcome to the MCQ Application!", Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public void displayQuestion(Question question) {
        // Implement Android-specific question display
        // You'll need to create an Android layout and update it here
    }

    @Override
    public String getUserInput() {
        // Implement Android-specific user input method
        // You'll need to create an input mechanism and return the user's answer
        return "";
    }

    @Override
    public void showCorrectAnswerFeedback() {
        activity.runOnUiThread(() -> {
            Toast.makeText(activity, "Correct! Keep going!", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void showGameOverScreen(int score, int streak) {
        activity.runOnUiThread(() -> {
            // You'll need to create and show a game over dialog or activity
            Toast.makeText(activity, "Game Over! Score: " + score + ", Streak: " + streak, Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public void showTimeUpScreen(int score, int streak) {
        activity.runOnUiThread(() -> {
            // You'll need to create and show a time up dialog or activity
            Toast.makeText(activity, "Time's up! Score: " + score + ", Streak: " + streak, Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public void showGameCompletedScreen(int score, int streak) {
        activity.runOnUiThread(() -> {
            // You'll need to create and show a game completed dialog or activity
            Toast.makeText(activity, "Game Completed! Final Score: " + score + ", Longest Streak: " + streak, Toast.LENGTH_LONG).show();
        });
    }
}