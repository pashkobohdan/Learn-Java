package com.pashkobohdan.learnjava.library.lessonsFirebaseWorker;

/**
 * Created by bohdan on 27.07.16.
 */
public class Test {
    private String text;
    private String theme;

    public Test() {
    }

    public Test(String theme, String text) {
        this.theme = theme;
        this.text = text;
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
}
