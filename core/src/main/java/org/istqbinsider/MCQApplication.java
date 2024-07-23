package org.istqbinsider;

import org.istqbinsider.controller.GameController;
import org.istqbinsider.ui.ConsoleUI;
import org.istqbinsider.ui.UI;
import org.istqbinsider.util.QuestionLoader;
import org.istqbinsider.util.XMLParser;

public class MCQApplication {
    public static void main(String[] args) {
        UI ui = new ConsoleUI();
        QuestionLoader questionLoader = new XMLParser(); // XMLParses реализует questionloader
        GameController gameController = new GameController(questionLoader);
        gameController.setUI(ui);
        gameController.startGame();
        gameController.startSurvivalMode();
    }
}