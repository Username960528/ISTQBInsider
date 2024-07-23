package org.istqbinsider.android.util;

import android.content.Context;
import org.istqbinsider.model.Question;
import org.istqbinsider.util.QuestionLoader;
import org.istqbinsider.util.XMLParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AndroidQuestionLoader implements QuestionLoader {
    private Context context;
    private XMLParser xmlParser;

    public AndroidQuestionLoader(Context context) {
        this.context = context;
        this.xmlParser = new XMLParser();
    }

    @Override
    public List<Question> loadQuestions() {
        try {
            InputStream inputStream = context.getAssets().open("questions.xml");
            System.out.println("Successfully opened questions.xml");
            List<Question> questions = xmlParser.parseQuestions(inputStream);
            System.out.println("Parsed " + questions.size() + " questions");
            return questions;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading questions: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}