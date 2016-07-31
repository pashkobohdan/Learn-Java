package com.pashkobohdan.learnjava.library.lessonsFirebaseWorker;

/**
 * Created by bohdan on 26.07.16.
 */
public class Theme {
    private String name;
    private String pic;
    private String part;
    private String text;

    public Theme() {
    }

    public Theme(String name, String pic, String part, String text) {
        this.name = name;
        this.pic = pic;
        this.part = part;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public String getPic() {
        return pic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
