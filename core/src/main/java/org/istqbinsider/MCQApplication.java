package org.istqbinsider;

import org.istqbinsider.controller.GameController;
import org.istqbinsider.ui.ConsoleUI;
import org.istqbinsider.ui.UI;
import org.istqbinsider.util.QuestionLoader;
import org.istqbinsider.util.XMLParser;

public class MCQApplication {
    public static void main(String[] args) {
        UI ui = new ConsoleUI();
        QuestionLoader questionLoader = new XMLParser(); // Assuming XMLParser implements QuestionLoader
        GameController gameController = new GameController(ui, questionLoader);
        gameController.startGame();
        gameController.startSurvivalMode();
    }
}