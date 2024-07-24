package org.istqbinsider.android.activities;
import org.istqbinsider.android.R;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.istqbinsider.android.AndroidUI;
import org.istqbinsider.android.util.AndroidQuestionLoader;
import org.istqbinsider.controller.GameController;
import org.istqbinsider.ui.UI;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private GameController gameController;
    private AndroidUI androidUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new LoadQuestionsTask().execute();
    }

    private class LoadQuestionsTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doIn`Background(Void... voids) {
            AndroidQuestionLoader questionLoader = new AndroidQuestionLoader(MainActivity.this);
            gameController = new GameController(questionLoader);
            androidUI = new AndroidUI(MainActivity.this, gameController);
            gameController.setUI(androidUI);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
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
}