package org.istqbinsider;

import org.istqbinsider.controller.GameController;
import org.istqbinsider.ui.ConsoleUI;
import org.istqbinsider.ui.UI;

public class MCQApplication {
    public static void main(String[] args) {
        UI ui = new ConsoleUI();
        GameController gameController = new GameController(ui);
        gameController.startGame();
    }
}