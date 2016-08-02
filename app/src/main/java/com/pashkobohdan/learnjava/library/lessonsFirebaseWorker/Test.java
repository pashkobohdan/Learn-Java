package com.pashkobohdan.learnjava.library.lessonsFirebaseWorker;

/**
 * Created by bohdan on 27.07.16.
 */
public class Test {
    private String text;
    private String theme;
    private String answers;
    private int oneAnswer;
    private int answer;

    public Test() {
    }

    public Test(String text, String theme, String answers, int oneAnswer, int answer) {
        this.setText(text);
        this.setTheme(theme);
        this.setAnswers(answers);
        this.setOneAnswer(oneAnswer);
        this.setAnswer(answer);
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

    public int getOneAnswer() {
        return oneAnswer;
    }

    public void setOneAnswer(int oneAnswer) {
        this.oneAnswer = oneAnswer;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
