package org.istqbinsider;

import org.istqbinsider.controller.GameController;

public class MCQApplication {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.startGame();
    }
}