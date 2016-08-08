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
import com.pashkobohdan.learnjava.library.adapters.ThemesListAdapter;
import com.pashkobohdan.learnjava.library.lessonsFirebaseWorker.Theme;

import java.io.File;
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

        LessonHolders lessonHolders = new LessonHolders();
        for (String lessonPart : parts) {
            String part = lessonPart.substring(lessonPart.indexOf("<")).replaceAll("\\\\n", "\n");

            if (part.indexOf("<text ") == 0) {
                part = part.replaceFirst("<text ", "");

                LessonHolders.LessonTextHolder textHolder = lessonHolders.getLessonText(context);
                textHolder.lessonText.setText(part);

                views.add(textHolder.getView());
            } else if (part.indexOf("<pic ") == 0) {
                part = part.replaceFirst("<pic ", "");

                int pos = part.indexOf(" ");
                String picName = part.substring(0, pos);
                String picTitle = pos < part.length() - 1 ? part.substring(pos + 1) : "";

                LessonHolders.LessonImageHolder imageHolder = lessonHolders.getLessonImage(context);

                ThemesListAdapter.setImageToImageView(imageHolder.lessonImage, new File(context.getFilesDir(), picName));
                imageHolder.lessonImageTitle.setText(picTitle);

                views.add(imageHolder.getView());
            } else if (part.indexOf("<code ") == 0) {
                part = part.replaceFirst("<code ", "");

                LessonHolders.LessonCodeHolder textHolder = lessonHolders.getLessonCode(context);
                textHolder.lessonCode.setText(part);

                views.add(textHolder.getView());
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
