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
            if (inputStream == null) {
                System.out.println("Failed to load resource: " + filename);
                return new ArrayList<>();
            }
            return parseQuestions(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    public List<Question> parseQuestions(InputStream inputStream) throws Exception {
        List<Question> questions = new ArrayList<>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("question");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String text = eElement.getElementsByTagName("questionName").item(0).getTextContent();
                    String correctAnswer = eElement.getElementsByTagName("answer").item(0).getTextContent();
                    List<String> options = new ArrayList<>();
                    options.add(eElement.getElementsByTagName("optionA").item(0).getTextContent());
                    options.add(eElement.getElementsByTagName("optionB").item(0).getTextContent());
                    options.add(eElement.getElementsByTagName("optionC").item(0).getTextContent());
                    options.add(eElement.getElementsByTagName("optionD").item(0).getTextContent());
                    questions.add(new Question(text, options, correctAnswer));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e; // Rethrow to be caught in AndroidQuestionLoader
        }
        return questions;
    }
}
