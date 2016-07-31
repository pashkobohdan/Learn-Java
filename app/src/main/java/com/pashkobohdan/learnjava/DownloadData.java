package com.pashkobohdan.learnjava;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.pashkobohdan.learnjava.library.dateBaseHelper.ReadData;
import com.pashkobohdan.learnjava.library.lessonsFirebaseWorker.Part;
import com.pashkobohdan.learnjava.library.lessonsFirebaseWorker.Test;
import com.pashkobohdan.learnjava.library.lessonsFirebaseWorker.Theme;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

public class DownloadData extends AppCompatActivity {

    private TextView status;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_data);

        status = (TextView) findViewById(R.id.text_status);
        progress = (ProgressBar) findViewById(R.id.progressBar);

        status.setText("Downloading data ...");
        progress.setVisibility(ProgressBar.VISIBLE);

        downloadAll();
    }

    private void downloadAll() {
        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("tests");

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    status.setText("Downloading tests");

                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        Test test = child.getValue(Test.class);

                        ReadData.addTest(test);
                    }

                    downloadThemes();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    progress.setVisibility(ProgressBar.INVISIBLE);

                    showBDError();
                }
            });
        } catch (Exception e) {
            showBDError();
        }
    }

    private void downloadThemes() {
        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Themes");

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    status.setText("Downloading themes");

                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        Theme theme = child.getValue(Theme.class);

                        ReadData.addTheme(theme);
                    }

                    downloadParts();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    progress.setVisibility(ProgressBar.INVISIBLE);

                    showBDError();
                }
            });
        } catch (Exception e) {
            showBDError();
        }
    }

    private void downloadParts() {
        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Parts");


            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    status.setText("Downloading parts");

                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        Part part = child.getValue(Part.class);

                        ReadData.addPart(part);
                    }

                    downloadPartsPictures();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    progress.setVisibility(ProgressBar.INVISIBLE);

                    showBDError();
                }
            });
        } catch (Exception e) {
            showBDError();
        }
    }

    private AtomicInteger totalPartsPic;
    private AtomicInteger totalThemesPic;

    private void downloadPartsPictures() {
        totalPartsPic = new AtomicInteger(ReadData.getPartsList().size());

        status.setText("Downloading parts pictures : ");

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://learn-java-d3304.appspot.com");
        StorageReference partsPicRef = storageRef.child("partsPic");

        for (final Part part : ReadData.getPartsList()) {
            partsPicRef.child(part.getPic()).getFile(new File(getBaseContext().getFilesDir(), part.getPic())).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    totalPartsPic.decrementAndGet();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Toast.makeText(DownloadData.this, "error : " + part.getPic() + "\n" + exception.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (totalPartsPic.get() > 0) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        downloadThemesPictures();
                    }
                });
            }
        }).start();
    }

    private void downloadThemesPictures() {
        totalThemesPic = new AtomicInteger(ReadData.getThemesList().size());

        status.setText("Downloading themes pictures : ");

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://learn-java-d3304.appspot.com").child("themesPic");

        for (Theme theme : ReadData.getThemesList()) {
            storageRef.child(theme.getPic()).getFile(new File(getBaseContext().getFilesDir(), theme.getPic())).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    totalThemesPic.decrementAndGet();
                }
            });
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (totalThemesPic.get() > 0) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progress.setVisibility(ProgressBar.INVISIBLE);
                        status.setText("Successfully");
                        Intent intent = new Intent(DownloadData.this, Parts.class);
                        startActivity(intent);
                    }
                });
            }
        }).start();
    }

    private void showBDError() {
        status.setText("Database connect error.\nTry again later");
        Toast.makeText(getBaseContext(), "Database connect error. Try again later", Toast.LENGTH_SHORT).show();
    }
}
