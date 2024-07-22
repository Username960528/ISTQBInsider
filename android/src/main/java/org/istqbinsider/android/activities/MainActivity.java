package org.istqbinsider.android.activities;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.istqbinsider.android.AndroidUI;
import org.istqbinsider.controller.GameController;
import org.istqbinsider.ui.UI;
import org.istqbinsider.android.R;

public class MainActivity extends AppCompatActivity {
    private GameController gameController;
    private AndroidUI androidUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UI androidUI = new AndroidUI(this);
        gameController = new GameController(androidUI);

        Button startSurvivalModeButton = findViewById(R.id.startSurvivalModeButton);
        startSurvivalModeButton.setOnClickListener(v -> {
            gameController.startSurvivalMode();
        });

        gameController.startGame();
    }
    private void startGameOnNewThread() {
        new Thread(() -> {
            gameController.startSurvivalMode();
        }).start();
    }
}