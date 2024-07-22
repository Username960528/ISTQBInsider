package org.istqbinsider.util;

import org.istqbinsider.model.Question;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {
    public List<Question> parseQuestions(String filename) {
        List<Question> questions = new ArrayList<>();
        try {
            File inputFile = new File(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("question");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String questionText = eElement.getElementsByTagName("text").item(0).getTextContent();
                    String correctAnswer = eElement.getElementsByTagName("correct_answer").item(0).getTextContent();
                    List<String> options = new ArrayList<>();
                    NodeList optionList = eElement.getElementsByTagName("option");
                    for (int i = 0; i < optionList.getLength(); i++) {
                        options.add(optionList.item(i).getTextContent());
                    }
                    questions.add(new Question(questionText, options, correctAnswer));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questions;
    }
}