package org.istqbinsider.android.util;

import android.content.Context;
import org.istqbinsider.model.Question;
import org.istqbinsider.util.QuestionLoader;
import org.istqbinsider.util.XMLParser;

import java.io.InputStream;
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
            return xmlParser.parseQuestions(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}