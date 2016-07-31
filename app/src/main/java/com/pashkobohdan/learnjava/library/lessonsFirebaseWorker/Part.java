package com.pashkobohdan.learnjava.library.lessonsFirebaseWorker;

/**
 * Created by bohdan on 27.07.16.
 */
public class Part {
    private String name;
    private String pic;

    public Part() {
    }

    public Part(String name, String pic) {
        this.name = name;
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
