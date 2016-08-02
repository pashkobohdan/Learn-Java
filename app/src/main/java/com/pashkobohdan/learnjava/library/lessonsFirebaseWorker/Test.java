package com.pashkobohdan.learnjava.library.lessonsFirebaseWorker;

/**
 * Created by bohdan on 27.07.16.
 */
public class Test {
    private String text;
    private String theme;
    private String answers;
    private int oneAnswer;

    public Test() {
    }

    public Test(String text, String theme, String answers, int oneAnswer) {
        this.text = text;
        this.theme = theme;
        this.answers = answers;
        this.oneAnswer = oneAnswer;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public int isOneAnswer() {
        return oneAnswer;
    }

    public void setOneAnswer(int oneAnswer) {
        this.oneAnswer = oneAnswer;
    }
}
