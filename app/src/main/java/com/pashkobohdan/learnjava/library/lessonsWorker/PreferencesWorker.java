package com.pashkobohdan.learnjava.library.lessonsWorker;

import android.content.Context;
import android.content.SharedPreferences;

import com.pashkobohdan.learnjava.library.lessonsFirebaseWorker.Part;
import com.pashkobohdan.learnjava.library.lessonsFirebaseWorker.Theme;

/**
 * Created by Bohdan Pashko on 07.08.16.
 */
public class PreferencesWorker {
    private static Context context;
    private static SharedPreferences partsPref;
    private static SharedPreferences partsCountPref;
    private static SharedPreferences themesPref;
    private static SharedPreferences themesCountPref;

    public static void setContext(Context con) {
        context = con;

        partsPref = context.getSharedPreferences("parts_pref", Context.MODE_PRIVATE);
        themesCountPref = context.getSharedPreferences("parts_count_pref", Context.MODE_PRIVATE);
        themesPref = context.getSharedPreferences("themes_pref", Context.MODE_PRIVATE);
        themesCountPref = context.getSharedPreferences("tests_count_pref", Context.MODE_PRIVATE);
    }

    public static boolean isPartOpen(Part part) {
        try {
            return partsPref.getBoolean(part.getName(), false);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isThemeOpen(Theme theme) {
        try {
            return themesPref.getBoolean(theme.getName(), false);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean openPart(Part part) {
        try {
            SharedPreferences.Editor editor = partsPref.edit();
            editor.putBoolean(part.getName(), true);
            editor.commit();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean openTheme(Theme theme) {
        try {
            SharedPreferences.Editor editor = themesPref.edit();
            editor.putBoolean(theme.getName(), true);
            editor.commit();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean plusOneTest(Theme theme) {
        try {
            SharedPreferences.Editor editor = themesCountPref.edit();
            editor.putInt(theme.getName(), themesCountPref.getInt(theme.getName(), 0) + 1);
            editor.commit();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean plusOnePart(Part part) {
        try {
            SharedPreferences.Editor editor = partsCountPref.edit();
            editor.putInt(part.getName(), themesCountPref.getInt(part.getName(), 0) + 1);
            editor.commit();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static int getOpenThemeCount(Part part) {
        try {
            return partsCountPref.getInt(part.getName(), 0);
        } catch (Exception e) {
            return 0;
        }
    }

    public static int getSuccessTestsCount(Theme theme) {
        try {
            return themesCountPref.getInt(theme.getName(), 0);
        } catch (Exception e) {
            return 0;
        }
    }
}
