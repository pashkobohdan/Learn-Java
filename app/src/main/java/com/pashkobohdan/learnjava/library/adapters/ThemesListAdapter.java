package com.pashkobohdan.learnjava.library.adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pashkobohdan.learnjava.Lesson;
import com.pashkobohdan.learnjava.R;
import com.pashkobohdan.learnjava.library.lessonsFirebaseWorker.Theme;

import java.io.File;
import java.util.List;

/**
 * Created by Bohdan Pashko on 29.07.16.
 */
public class ThemesListAdapter extends RecyclerView.Adapter<ThemesListAdapter.ViewHolder> {
    private List<Theme> themes;
    Activity context;
    private RecyclerView recyclerView;

    public ThemesListAdapter(Activity context, List<Theme> themes, RecyclerView recyclerView) {
        this.themes = themes;
        this.context = context;
        this.recyclerView = recyclerView;
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView partName;
        protected ImageView partImage;


        public ViewHolder(View itemView) {
            super(itemView);
            partName = (TextView) itemView.findViewById(R.id.part_name);
            partImage = (ImageView) itemView.findViewById(R.id.part_image);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.themes_list_row, null, false);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Theme theme = themes.get(recyclerView.indexOfChild(view));

                Intent intent = new Intent(context, Lesson.class);
                intent.putExtra("theme_name", theme.getName());

                context.startActivity(intent);
            }
        });

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Theme theme = getThemes().get(position);

        holder.partName.setText(theme.getName());
        setImageToImageView(holder.partImage, new File(context.getFilesDir(), theme.getPic()));
    }

    public static boolean setImageToImageView(ImageView imageView, File fileWithImage) {
        if (fileWithImage.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(fileWithImage.getAbsolutePath());
            imageView.setImageBitmap(myBitmap);

            return true;
        }
        return false;
    }

    @Override
    public int getItemCount() {
        return getThemes().size();
    }
}
