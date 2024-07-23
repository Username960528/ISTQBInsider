package org.istqbinsider.android.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.istqbinsider.android.AndroidUI;
import org.istqbinsider.android.util.AndroidQuestionLoader;
import org.istqbinsider.controller.GameController;
import org.istqbinsider.ui.UI;
import org.istqbinsider.android.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private GameController gameController;
    private AndroidUI androidUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AndroidQuestionLoader questionLoader = new AndroidQuestionLoader(this);
        gameController = new GameController(questionLoader);
        androidUI = new AndroidUI(this, gameController);
        gameController.setUI(androidUI);

        Button startSurvivalModeButton = findViewById(R.id.startSurvivalModeButton);
        startSurvivalModeButton.setOnClickListener(v -> {
            Log.d(TAG, "Start Survival Mode button clicked");
            new Thread(() -> {
                gameController.startSurvivalMode();
            }).start();
        });

        gameController.startGame();
    }
}

