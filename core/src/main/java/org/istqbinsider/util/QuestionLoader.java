package org.istqbinsider.util;

import org.istqbinsider.model.Question;
import java.util.List;

public interface QuestionLoader {
    List<Question> loadQuestions();
}