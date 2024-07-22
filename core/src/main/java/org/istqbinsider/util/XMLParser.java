package org.istqbinsider.util;

import org.istqbinsider.model.Question;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XMLParser implements QuestionLoader {
    @Override
    public List<Question> loadQuestions() {
        return parseQuestionsFromFile("questions.xml");
    }

    public List<Question> parseQuestionsFromFile(String filename) {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/" + filename);
            return parseQuestions(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Question> parseQuestions(InputStream inputStream) throws Exception {
        List<Question> questions = new ArrayList<>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputStream);
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("question");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String text = eElement.getElementsByTagName("text").item(0).getTextContent();
                String correctAnswer = eElement.getElementsByTagName("correct_answer").item(0).getTextContent();
                List<String> options = new ArrayList<>();
                NodeList optionNodes = eElement.getElementsByTagName("option");
                for (int i = 0; i < optionNodes.getLength(); i++) {
                    options.add(optionNodes.item(i).getTextContent());
                }
                questions.add(new Question(text, options, correctAnswer));
            }
        }
        return questions;
    }
}