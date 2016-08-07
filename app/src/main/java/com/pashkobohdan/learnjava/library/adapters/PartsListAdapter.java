package com.pashkobohdan.learnjava.library.adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pashkobohdan.learnjava.R;
import com.pashkobohdan.learnjava.library.dateBaseHelper.ReadData;
import com.pashkobohdan.learnjava.library.lessonsFirebaseWorker.Part;
import com.pashkobohdan.learnjava.library.lessonsWorker.PreferencesWorker;

import java.io.File;
import java.util.List;

/**
 * Created by Bohdan Pashko on 29.07.16.
 */
public class PartsListAdapter extends ArrayAdapter<Part> {
    private final Activity context;
    private final List<Part> parts;


    public PartsListAdapter(Activity context, List<Part> parts) {
        super(context, R.layout.parts_list_row, parts);
        this.context = context;
        this.parts = parts;
    }

    static class ViewSubjectHolder {
        protected TextView partName;
        protected ImageView partImage;
        protected TextView themeNumber;
    }


    @Override
    public int getCount() {
        return parts.size();
    }

    @Override
    public Part getItem(int position) {
        return parts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {

        View view = null;
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.parts_list_row, null);

            final ViewSubjectHolder viewHolder = new ViewSubjectHolder();
            viewHolder.partName = (TextView) view.findViewById(R.id.part_name);
            viewHolder.partImage = (ImageView) view.findViewById(R.id.part_image);
            viewHolder.themeNumber = (TextView) view.findViewById(R.id.current_theme_number);

            view.setTag(viewHolder);

            viewHolder.partName.setText(parts.get(position).getName());
            viewHolder.themeNumber.setText(PreferencesWorker.getOpenThemeCount(parts.get(position))
                    + " / "
                    + ReadData.getThemesByPart(parts.get(position).getName()).size());
            setImageToImageView(viewHolder.partImage, new File(context.getFilesDir(), parts.get(position).getPic()));

        } else {
            view = convertView;

            final ViewSubjectHolder holder = (ViewSubjectHolder) view.getTag();
            holder.partName.setText(parts.get(position).getName());
            holder.themeNumber.setText(PreferencesWorker.getOpenThemeCount(parts.get(position))
                    + " / "
                    + ReadData.getThemesByPart(parts.get(position).getName()).size());
            setImageToImageView(holder.partImage, new File(context.getFilesDir(), parts.get(position).getPic()));
        }

        return view;
    }

    public static boolean setImageToImageView(ImageView imageView, File fileWithImage) {
        if (fileWithImage.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(fileWithImage.getAbsolutePath());
            imageView.setImageBitmap(myBitmap);

            return true;
        }
        return false;
    }
}
