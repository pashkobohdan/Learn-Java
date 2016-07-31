package com.pashkobohdan.learnjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.pashkobohdan.learnjava.library.adapters.PartsListAdapter;
import com.pashkobohdan.learnjava.library.dateBaseHelper.ReadData;

public class Parts extends AppCompatActivity {
    private Toolbar toolbar;

    private ListView partListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parts);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Learn Java");
        setSupportActionBar(toolbar);


        ReadData.setContext(getBaseContext());
        ReadData.refreshParts();

        if (ReadData.getPartsList().size() == 0) {
            Intent intent = new Intent(Parts.this, DownloadData.class);
            startActivity(intent);
        }

        partListView = (ListView) findViewById(R.id.partsListView);


        PartsListAdapter booksInfoFirebaseListAdapter = new PartsListAdapter(Parts.this, ReadData.getPartsList());
        partListView.setAdapter(booksInfoFirebaseListAdapter);

        partListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Parts.this, "number : " + i, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Parts.this, Themes.class);
                intent.putExtra("part_number", ReadData.getPartsList().get(i).getName());
                intent.putExtra("part_image", ReadData.getPartsList().get(i).getPic());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_parts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.reload_db) {
            ReadData.dropAllTables();

            ReadData.refreshParts();
            if (ReadData.getPartsList().size() == 0) {
                Intent intent = new Intent(Parts.this, DownloadData.class);
                startActivity(intent);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


