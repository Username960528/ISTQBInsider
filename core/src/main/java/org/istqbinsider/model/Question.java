package org.istqbinsider.model;

import java.util.List;

public class Question {
    private String questionText;
    private List<String> options;
    private String correctAnswer;

    public Question(String questionText, List<String> options, String correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    // Getters
    public String getQuestionText() { return questionText; }
    public List<String> getOptions() { return options; }
    public String getCorrectAnswer() { return correctAnswer; }

    // New methods
    public String getOptionA() {
        return options.size() > 0 ? options.get(0) : "";
    }

    public String getOptionB() {
        return options.size() > 1 ? options.get(1) : "";
    }

    public String getOptionC() {
        return options.size() > 2 ? options.get(2) : "";
    }

    public String getOptionD() {
        return options.size() > 3 ? options.get(3) : "";
    }
}