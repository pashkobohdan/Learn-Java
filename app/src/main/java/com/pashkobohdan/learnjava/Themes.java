package com.pashkobohdan.learnjava;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.pashkobohdan.learnjava.library.adapters.ThemesListAdapter;
import com.pashkobohdan.learnjava.library.dateBaseHelper.ReadData;

import java.io.File;

public class Themes extends AppCompatActivity {
    private CoordinatorLayout coordinatorLayout;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    ThemesListAdapter themesListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themes);

        ReadData.refreshThemes();


        Bundle extras = getIntent().getExtras();
        String themeName= extras.getString("part_number");
        String themeImage= extras.getString("part_image");


        ImageView bacImageView = (ImageView)findViewById(R.id.background_image);
        ThemesListAdapter.setImageToImageView(bacImageView, new File(getBaseContext().getFilesDir(), themeImage));


        if (themeName != null) {

            recyclerView = (RecyclerView) findViewById(R.id.themes_list_view);

            mLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(mLayoutManager);

            themesListAdapter = new ThemesListAdapter(Themes.this, ReadData.getThemesByPart(themeName),recyclerView);
            recyclerView.setAdapter(themesListAdapter);
        }

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
        }

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(getString(R.string.app_name));
    }
}
