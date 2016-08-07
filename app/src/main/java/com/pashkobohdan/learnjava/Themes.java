package com.pashkobohdan.learnjava;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.pashkobohdan.learnjava.library.adapters.ThemesListAdapter;
import com.pashkobohdan.learnjava.library.dateBaseHelper.ReadData;

import java.io.File;

public class Themes extends AppCompatActivity {
    private CoordinatorLayout coordinatorLayout;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    String partName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themes);

        Bundle extras = getIntent().getExtras();
        partName = extras.getString("part_number");
        String partImage = extras.getString("part_image");

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
        }

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(partName);

        ImageView bacImageView = (ImageView) findViewById(R.id.background_image);
        ThemesListAdapter.setImageToImageView(bacImageView, new File(getBaseContext().getFilesDir(), partImage));


        recyclerView = (RecyclerView) findViewById(R.id.themes_list_view);

        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        ThemesListAdapter themesListAdapter = new ThemesListAdapter(Themes.this, ReadData.getThemesByPart(partName), recyclerView);
        recyclerView.setAdapter(themesListAdapter);


        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);
    }

    @Override
    protected void onResume() {
        super.onResume();

        recyclerView.setAdapter(null);

        ThemesListAdapter themesListAdapter = new ThemesListAdapter(Themes.this, ReadData.getThemesByPart(partName), recyclerView);
        recyclerView.setAdapter(themesListAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
