package com.pashkobohdan.learnjava.library.dateBaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.pashkobohdan.learnjava.library.lessonsFirebaseWorker.Part;
import com.pashkobohdan.learnjava.library.lessonsFirebaseWorker.Test;
import com.pashkobohdan.learnjava.library.lessonsFirebaseWorker.Theme;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Bohdan Pashko on 17.05.2016.
 */
public class ReadData {
    public static final String DATABASE_NAME = "javalearn.db";
    public static final int DATABASE_VERSION = 1;

    private static List<Test> testsList = new LinkedList<>();
    private static List<Theme> themesList = new LinkedList<>();
    private static List<Part> partsList = new LinkedList<>();

    private static DatabaseHelper mDatabaseHelper;
    private static SQLiteDatabase mSqLiteDatabase;

    private static Context context;

    // refresh
    public static boolean refreshTests() {
        List<Test> resultList = new LinkedList<>();

        try {
            Cursor cursorEvents = mSqLiteDatabase.query(DatabaseHelper.TEST_DATABASE_TABLE, new String[]{
                            DatabaseHelper.TEST_ID,
                            DatabaseHelper.TEST_TEXT,
                            DatabaseHelper.TEST_THEME,
                            DatabaseHelper.TEST_ANSWERS,
                            DatabaseHelper.TEST_ONE_ANSWER,
                            DatabaseHelper.TEST_ANSWER},
                    null, null,
                    null, null, null);
            int testId, oneAnswer, answer;
            String testText, testTheme, testAnswers;
            Test test;

            while (cursorEvents.moveToNext()) {
                testId = cursorEvents.getInt(cursorEvents.getColumnIndex(DatabaseHelper.TEST_ID));
                testText = cursorEvents.getString(cursorEvents.getColumnIndex(DatabaseHelper.TEST_TEXT));
                testTheme = cursorEvents.getString(cursorEvents.getColumnIndex(DatabaseHelper.TEST_THEME));
                testAnswers = cursorEvents.getString(cursorEvents.getColumnIndex(DatabaseHelper.TEST_ANSWERS));
                oneAnswer = cursorEvents.getInt(cursorEvents.getColumnIndex(DatabaseHelper.TEST_ONE_ANSWER));
                answer = cursorEvents.getInt(cursorEvents.getColumnIndex(DatabaseHelper.TEST_ANSWER));

                test = new Test(testText,testTheme,testAnswers,oneAnswer,answer);

                resultList.add(test);

            }
            cursorEvents.close();

            testsList = resultList;
            return true;
        } catch (Exception e) {
            Toast.makeText(context, e.toString()+"", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return false;
        }
    }

    public static boolean refreshThemes() {
        List<Theme> resultList = new LinkedList<>();

        try {
            Cursor cursorEvents = mSqLiteDatabase.query(DatabaseHelper.THEME_DATABASE_TABLE, new String[]{
                            DatabaseHelper.THEME_ID,
                            DatabaseHelper.THEME_NAME,
                            DatabaseHelper.THEME_PART,
                            DatabaseHelper.THEME_PIC,
                            DatabaseHelper.THEME_TEXT},
                    null, null,
                    null, null, null);


            int themeID;
            String themeName, themePart, themePic, themeText;
            Theme theme = null;

            while (cursorEvents.moveToNext()) {
                themeID = cursorEvents.getInt(cursorEvents.getColumnIndex(DatabaseHelper.THEME_ID));
                themeName = cursorEvents.getString(cursorEvents.getColumnIndex(DatabaseHelper.THEME_NAME));
                themePart = cursorEvents.getString(cursorEvents.getColumnIndex(DatabaseHelper.THEME_PART));
                themePic = cursorEvents.getString(cursorEvents.getColumnIndex(DatabaseHelper.THEME_PIC));
                themeText = cursorEvents.getString(cursorEvents.getColumnIndex(DatabaseHelper.THEME_TEXT));

                theme = new Theme(themeName, themePic, themePart, themeText);

                resultList.add(theme);
            }
            cursorEvents.close();

            themesList = resultList;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean refreshParts() {
        List<Part> resultList = new LinkedList<>();

        try {
            Cursor cursorEvents = mSqLiteDatabase.query(DatabaseHelper.PARTS_DATABASE_TABLE, new String[]{
                            DatabaseHelper.PART_ID,
                            DatabaseHelper.PART_NAME,
                            DatabaseHelper.PART_PIC},
                    null, null,
                    null, null, null);


            int partID;
            String partName, partPic;
            Part part = null;

            //read data !!!!!!!!!!! from database

            while (cursorEvents.moveToNext()) {
                partID = cursorEvents.getInt(cursorEvents.getColumnIndex(DatabaseHelper.PART_ID));

                partName = cursorEvents.getString(cursorEvents.getColumnIndex(DatabaseHelper.PART_NAME));
                partPic = cursorEvents.getString(cursorEvents.getColumnIndex(DatabaseHelper.PART_PIC));

                part = new Part(partName, partPic);

                resultList.add(part);
            }
            cursorEvents.close();

            partsList = resultList;
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static List<Theme> getThemesByPart(String partName) {
        List<Theme> result = new LinkedList<>();

        for (Theme theme : getThemesList()) {
            if (theme.getPart().equals(partName)) {
                result.add(theme);
            }
        }

        return result;
    }

    public static List<Test> getTestByTheme (String themeName) {
        List<Test> result = new LinkedList<>();

        for (Test test : getTestsList()) {
            if (test.getTheme().equals(themeName)) {
                result.add(test);
            }
        }

        return result;
    }


    public static void dropAllTables() {
        mSqLiteDatabase.execSQL("delete from " + DatabaseHelper.PARTS_DATABASE_TABLE);
        mSqLiteDatabase.execSQL("delete from " + DatabaseHelper.THEME_DATABASE_TABLE);
        mSqLiteDatabase.execSQL("delete from " + DatabaseHelper.TEST_DATABASE_TABLE);
    }
//    //delete
//    public static boolean deleteSubject(Subject subject) {
//        mSqLiteDatabase.delete(DatabaseHelper.SUBJECT_DATABASE_TABLE,
//                mDatabaseHelper.SUBJECT_ID + " = ?", new String[]{Integer.toString(subject.getId())});
//
//        return true;
//    }
//
//    public static boolean deleteLecture(Lecture lecture) {
//        mSqLiteDatabase.delete(DatabaseHelper.LECTURE_DATABASE_TABLE,
//                mDatabaseHelper.LECTURE_ID + " = ?", new String[]{Integer.toString(lecture.getLectureID())});
//
//        return true;
//    }
//
//    public static boolean deleteEvent(Event event) {
//        mSqLiteDatabase.delete(DatabaseHelper.EVENTS_DATABASE_TABLE,
//                mDatabaseHelper.EVENTS_ID + " = ?", new String[]{Integer.toString(event.getId())});
//
//        return true;
//    }
//    //
//
//
//    //edit
//    public static boolean editSubject(Subject subject) {
//        ContentValues values = new ContentValues();
//        values.put(DatabaseHelper.SUBJECT_NAME, subject.getName());
//        values.put(DatabaseHelper.HOURS, subject.getHours());
//        values.put(DatabaseHelper.TEACHER_NAME, subject.getTeacher());
//        values.put(DatabaseHelper.SUBJECT_TYPE, subject.getSubjectType());
//
//
//        mSqLiteDatabase.update(DatabaseHelper.SUBJECT_DATABASE_TABLE, values,
//                mDatabaseHelper.SUBJECT_ID + " = ?", new String[]{Integer.toString(subject.getId())});
//        return true;
//    }
//
//    public static boolean editLecture(Lecture lecture) {
//        ContentValues values = new ContentValues();
//        values.put(DatabaseHelper.SUBJECT_ID, lecture.getSubject().getId());
//        values.put(DatabaseHelper.DAY_OF_WEEK, Day.getStringDay(lecture.getDay()));
//        values.put(DatabaseHelper.START_TIME, lecture.getStartTime().toString());
//        values.put(DatabaseHelper.END_TIME, lecture.getEndTime().toString());
//        values.put(DatabaseHelper.NUMBER_OF_WEEK, lecture.getNumberOfWeek());
//
//
//        mSqLiteDatabase.update(DatabaseHelper.LECTURE_DATABASE_TABLE, values,
//                mDatabaseHelper.LECTURE_ID + " = ?", new String[]{Integer.toString(lecture.getLectureID())});
//        return true;
//    }
//
//    public static boolean editEvent(Event event) {
//        ContentValues values = new ContentValues();
//        values.put(DatabaseHelper.EVENT_NAME, event.getName());
//        values.put(DatabaseHelper.EVENT_TYPE, event.getType());
//        values.put(DatabaseHelper.EVENT_PRIORITY, event.getPriority());
//        values.put(DatabaseHelper.EVENT_LENGTH, event.getLength().toString());
//        values.put(DatabaseHelper.EVENT_END_TIME, new SimpleDateFormat("yyyy-MM-dd HH:mm").format(event.getCompleteDate()));
//        values.put(DatabaseHelper.EVENT_START_TIME, new SimpleDateFormat("yyyy-MM-dd HH:mm").format(event.getStartTime()));
//        values.put(DatabaseHelper.EVENT_IS_COMPLETE, event.isComplete());
//
//        return mSqLiteDatabase.update(DatabaseHelper.EVENTS_DATABASE_TABLE, values,
//                mDatabaseHelper.EVENTS_ID + " = ?", new String[]{Integer.toString(event.getId())}) == -1 ? false : true;
//    }
//    //

    //add
    public static boolean addPart(Part part) {
        partsList.add(part);

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.PART_NAME, part.getName());
        values.put(DatabaseHelper.PART_PIC, part.getPic());

        return mSqLiteDatabase.insert(DatabaseHelper.PARTS_DATABASE_TABLE, null, values) == -1 ? false : true;
    }

    public static boolean addTheme(Theme theme) {
        themesList.add(theme);

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.THEME_NAME, theme.getName());
        values.put(DatabaseHelper.THEME_PART, theme.getPart());
        values.put(DatabaseHelper.THEME_PIC, theme.getPic());
        values.put(DatabaseHelper.THEME_TEXT, theme.getText());

        return mSqLiteDatabase.insert(DatabaseHelper.THEME_DATABASE_TABLE, null, values) == -1 ? false : true;
    }

    public static boolean addTest(Test test) {
        testsList.add(test);

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.TEST_TEXT, test.getText());
        values.put(DatabaseHelper.TEST_ANSWERS, test.getAnswers());
        values.put(DatabaseHelper.TEST_ONE_ANSWER, test.getOneAnswer());
        values.put(DatabaseHelper.TEST_ANSWER, test.getAnswer());
        values.put(DatabaseHelper.TEST_THEME, test.getTheme());

        return mSqLiteDatabase.insert(DatabaseHelper.TEST_DATABASE_TABLE, null, values)== -1 ? false : true;
    }
    //


    public static boolean setContext(Context context) {
        ReadData.context = context;
        if (mDatabaseHelper == null) {
            mDatabaseHelper = new DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
            mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
            return true;
        } else {
            return false;
        }
    }

    public static Context getContext(){
        return context;
    }

    public static boolean isDBEmpty() {
        return DatabaseUtils.queryNumEntries(mSqLiteDatabase, DatabaseHelper.PARTS_DATABASE_TABLE) == 0;
    }

    public static List<Test> getTestsList() {
        return testsList;
    }

    public static List<Theme> getThemesList() {
        return themesList;
    }

    public static List<Part> getPartsList() {
        return partsList;
    }
}
