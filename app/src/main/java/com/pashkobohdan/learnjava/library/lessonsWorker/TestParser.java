package com.pashkobohdan.learnjava.library.lessonsWorker;

import com.pashkobohdan.learnjava.library.lessonsFirebaseWorker.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by bohdan on 02.08.16.
 */
public class TestParser {
    private String testText;
    private String testType;
    private List<String> answers;
    private int answer;


    public static TestParser newInstance(Test test) {
        String testText = test.getText();
        String testType = "Check 1 true answer";
        int answer = test.getAnswer();

        List<String> answers = new LinkedList<>();
        for (String ans : test.getAnswers().split("/>")) {
            answers.add(ans.substring(ans.indexOf(" ") + 1));
        }

        return new TestParser(testText, testType, answers, answer);
    }

    private TestParser(String testText, String testType, List<String> answers, int answer) {
        this.testText = testText;
        this.testType = testType;
        this.answers = answers;
        this.answer = answer;
    }

    public String getTestText() {
        return testText;
    }

    public String getTestType() {
        return testType;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getAnswer() {
        return answer;
    }
}
