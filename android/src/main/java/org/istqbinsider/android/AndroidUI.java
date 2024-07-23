package org.istqbinsider.android;

import org.istqbinsider.controller.GameController;
import org.istqbinsider.ui.UI;
import org.istqbinsider.model.Question;

import android.app.Activity;
import android.os.Build;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

public class AndroidUI implements UI {
    private Activity activity;
    private TextView questionTextView;
    private Button[] optionButtons;
    private Button startSurvivalModeButton;
    private GameController gameController;

    public AndroidUI(Activity activity, GameController gameController) {
        this.activity = activity;
        this.gameController = gameController;
        initializeUI();
    }

    private void initializeUI() {
        // Initialize UI components
        questionTextView = activity.findViewById(R.id.questionTextView);
        optionButtons = new Button[4];
        optionButtons[0] = activity.findViewById(R.id.optionAButton);
        optionButtons[1] = activity.findViewById(R.id.optionBButton);
        optionButtons[2] = activity.findViewById(R.id.optionCButton);
        optionButtons[3] = activity.findViewById(R.id.optionDButton);
        startSurvivalModeButton = activity.findViewById(R.id.startSurvivalModeButton);
    }

    @Override
    public void showWelcomeScreen() {
        activity.runOnUiThread(() -> {
            questionTextView.setText("Welcome to ISTQB Insider!");
            startSurvivalModeButton.setVisibility(View.VISIBLE);
            for (Button button : optionButtons) {
                button.setVisibility(View.GONE);
            }
            startSurvivalModeButton.setOnClickListener(v -> {
                gameController.startSurvivalMode();
            });
        });
    }

    @Override
    public void displayQuestion(Question question) {
        activity.runOnUiThread(() -> {
            questionTextView.setText(question.getQuestionText());
            optionButtons[0].setText(question.getOptionA());
            optionButtons[1].setText(question.getOptionB());
            optionButtons[2].setText(question.getOptionC());
            optionButtons[3].setText(question.getOptionD());

            for (Button button : optionButtons) {
                button.setVisibility(View.VISIBLE);
            }
        });
    }

    private CountDownLatch inputLatch;
    private String userAnswer;

    @Override
    public String getUserInput() {
        inputLatch = new CountDownLatch(1);
        userAnswer = null;

        for (int i = 0; i < optionButtons.length; i++) {
            final String option = String.valueOf((char) ('A' + i));
            optionButtons[i].setOnClickListener(v -> {
                userAnswer = option;
                inputLatch.countDown();
            });
        }

        try {
            inputLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return userAnswer;
    }

    public void showErrorMessage(String message) {
        activity.runOnUiThread(() -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle("Error")
                    .setMessage(message)
                    .setPositiveButton("OK", null)
                    .show();
        });
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
            Toast.makeText(activity, "Game Over! Score: " + score + ", Streak: " + streak, Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public void showTimeUpScreen(int score, int streak) {
        activity.runOnUiThread(() -> {
            Toast.makeText(activity, "Time's up! Score: " + score + ", Streak: " + streak, Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public void showGameCompletedScreen(int score, int streak) {
        activity.runOnUiThread(() -> {
            Toast.makeText(activity, "Game Completed! Final Score: " + score + ", Longest Streak: " + streak, Toast.LENGTH_LONG).show();
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setUserInputCallback(Consumer<String> callback) {
        for (int i = 0; i < optionButtons.length; i++) {
            final String option = String.valueOf((char) ('A' + i));
            optionButtons[i].setOnClickListener(v -> {
                callback.accept(option);
            });
        }
    }
}