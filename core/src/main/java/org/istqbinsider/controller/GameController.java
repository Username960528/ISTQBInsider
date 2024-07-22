package org.istqbinsider.controller;

import org.istqbinsider.model.Question;
import org.istqbinsider.ui.UI;
import org.istqbinsider.ui.ConsoleUI; // Add this import
import org.istqbinsider.util.Timer;
import org.istqbinsider.util.XMLParser;

import java.util.List;

public class GameController {
    private XMLParser xmlParser;
    private List<Question> questions;
    private UI ui;
    private Timer timer;
    private int score;
    private int streak;

    public GameController(UI ui) {
        this.ui = ui;
        xmlParser = new XMLParser();
        questions = xmlParser.parseQuestions("questions.xml");
//        ui = new ConsoleUI(); // Use ConsoleUI as the default implementation
        timer = new Timer();
    }

    public void startGame() {
        ui.showWelcomeScreen();
        startSurvivalMode();
    }

    private void startSurvivalMode() {
        score = 0;
        streak = 0;
        timer.start(60); // 60 seconds for survival mode

        for (Question question : questions) {
            ui.displayQuestion(question);
            String userAnswer = ui.getUserInput();

            if (validateAnswer(question, userAnswer)) {
                score++;
                streak++;
                ui.showCorrectAnswerFeedback();
            } else {
                ui.showGameOverScreen(score, streak);
                return;
            }

            if (timer.isTimeUp()) {
                ui.showTimeUpScreen(score, streak);
                return;
            }
        }

        ui.showGameCompletedScreen(score, streak);
    }

    private boolean validateAnswer(Question question, String userAnswer) {
        return question.getCorrectAnswer().equalsIgnoreCase(userAnswer);
    }
}