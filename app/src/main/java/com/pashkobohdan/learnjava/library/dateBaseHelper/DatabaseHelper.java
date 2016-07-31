package com.pashkobohdan.learnjava.library.dateBaseHelper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Bohdan Pashko on 05.05.2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "javalearn.db";
    private static final int DATABASE_VERSION = 1;


    // tests database
    public static final String TEST_DATABASE_TABLE = "tests";

    public static final String TEST_ID = "test_id";
    public static final String TEST_TEXT = "test_name";
    public static final String TEST_THEME = "test_theme";

    public static final String DATABASE_CREATE_TEST_SCRIPT = "create table "
            + TEST_DATABASE_TABLE + " ("
            + TEST_ID + " integer primary key autoincrement, "
            + TEST_TEXT + " text not null, "
            + TEST_THEME + " text not null);";


    // themes database
    public static final String THEME_DATABASE_TABLE = "themes";

    public static final String THEME_ID = "theme_id";
    public static final String THEME_NAME = "theme_name";
    public static final String THEME_PART = "theme_part";
    public static final String THEME_PIC = "theme_pic";
    public static final String THEME_TEXT = "theme_text";


    public static final String DATABASE_CREATE_THEME_SCRIPT = "create table "
            + THEME_DATABASE_TABLE + " ("
            + THEME_ID + " integer primary key autoincrement, "
            + THEME_NAME + " text, "
            + THEME_PART + " text, "
            + THEME_PIC + " text, "
            + THEME_TEXT + " text);";


    // parts database
    public static final String PARTS_DATABASE_TABLE = "parts";

    public static final String PART_ID = "part_id";
    public static final String PART_NAME = "part_name";
    public static final String PART_PIC = "pirt_pic";

    public static final String DATABASE_CREATE_PARTS_SCRIPT = "create table "
            + PARTS_DATABASE_TABLE + " ("
            + PART_ID + " integer primary key autoincrement, "
            + PART_NAME + " text, "
            + PART_PIC + " text);";


    Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        this.context = context;
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_TEST_SCRIPT);
        db.execSQL(DATABASE_CREATE_THEME_SCRIPT);
        db.execSQL(DATABASE_CREATE_PARTS_SCRIPT);

        Log.w("SQLite", "Create a new database");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("SQLite", "Обновляемся с версии " + oldVersion + " на версию " + newVersion);

        db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_CREATE_TEST_SCRIPT);
        db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_CREATE_THEME_SCRIPT);
        db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_CREATE_PARTS_SCRIPT);

        onCreate(db);
    }
}