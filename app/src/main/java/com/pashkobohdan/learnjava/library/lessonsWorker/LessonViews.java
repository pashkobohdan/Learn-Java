package com.pashkobohdan.learnjava.library.lessonsWorker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pashkobohdan.learnjava.Lesson;
import com.pashkobohdan.learnjava.R;
import com.pashkobohdan.learnjava.library.lessonsFirebaseWorker.Theme;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by bohdan on 31.07.16.
 */
public class LessonViews {
    private Theme currentTheme;
    private List<View> views;
    private Context context;


    public LessonViews(Theme currentTheme, Context context) {
        this.setCurrentTheme(currentTheme);
        this.context = context;

        convertAllData();
    }

    public void convertAllData() {
        views = new LinkedList<>();

        String[] parts = currentTheme.getText().split("/>");


        Toast.makeText(context, "text : "+currentTheme.getText(), Toast.LENGTH_SHORT).show();

        LessonHolders lessonHolders = new LessonHolders();
        for (String lessonPart : parts) {
            if (lessonPart.indexOf("<text ") == 0) {
                lessonPart = lessonPart.replaceFirst("<text ","");

                LessonHolders.LessonTextHolder textHolder = lessonHolders.getLessonText(context);
                textHolder.lessonText.setText(lessonPart);

                views.add(textHolder.getView());
            }
            if (lessonPart.indexOf("<pic ") == 0) {
                lessonPart = lessonPart.replaceFirst("<pic ","");

                String[] textAndImage = lessonPart.split(" ");


                LessonHolders.LessonImageHolder imageHolder = lessonHolders.getLessonImage(context);
                imageHolder.lessonImage.setImageDrawable(context.getResources().getDrawable(R.drawable.three_principles));
                imageHolder.lessonImageTitle.setText(textAndImage[1]);

                views.add(imageHolder.getView());
            }
        }
    }


    public Theme getCurrentTheme() {
        return currentTheme;
    }

    public void setCurrentTheme(Theme currentTheme) {
        this.currentTheme = currentTheme;
    }

    public List<View> getViews() {
        return views;
    }
}
