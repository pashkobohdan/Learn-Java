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

import com.pashkobohdan.learnjava.Lesson;
import com.pashkobohdan.learnjava.R;
import com.pashkobohdan.learnjava.library.lessonsFirebaseWorker.Theme;

/**
 * Created by bohdan on 31.07.16.
 */
public class LessonHolders {
    class LessonTextHolder extends RecyclerView.ViewHolder {
        protected TextView lessonText;
        private View view;

        public LessonTextHolder(View itemView) {
            super(itemView);

            this.view = itemView;

            lessonText = (TextView) itemView.findViewById(R.id.lesson_text);
        }

        public View getView() {
            return view;
        }
    }

    public LessonTextHolder getLessonText(Context context) {
        View v = LayoutInflater.from(context).inflate(R.layout.lesson_text, null, false);
        return new LessonTextHolder(v);
    }

    class LessonImageHolder extends RecyclerView.ViewHolder {
        protected ImageView lessonImage;
        protected TextView lessonImageTitle;
        private View view;

        public LessonImageHolder(View itemView) {
            super(itemView);

            this.view = itemView;

            lessonImage = (ImageView)itemView.findViewById(R.id.lesson_image);
            lessonImageTitle = (TextView) itemView.findViewById(R.id.lesson_image_title);
        }

        public View getView() {
            return view;
        }
    }

    public LessonImageHolder getLessonImage(Context context) {
        View v = LayoutInflater.from(context).inflate(R.layout.lesson_image, null, false);
        return new LessonImageHolder(v);
    }

}
