package org.istqbinsider.android.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import org.istqbinsider.android.AndroidUI;
import org.istqbinsider.controller.GameController;
import org.istqbinsider.ui.UI;
import org.istqbinsider.android.R;

public class MainActivity extends AppCompatActivity {
    private GameController gameController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UI androidUI = new AndroidUI(this);
        gameController = new GameController(androidUI);

        // Start the game or set up UI elements as needed
        gameController.startGame();
    }
}