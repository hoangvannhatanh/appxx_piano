package com.piano.keyboard.synthesia.learnpiano.play.music.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class NTDHelperSharePrefUtils {
    private static SharedPreferences sharedPreferences;

    public static final String SHARE_PREF_OPEN_FIRST_APP = "SHARE_PREF_OPEN_FIRST_APP";
    public static final String PIANO_TEMPO = "PIANO_TEMPO";
    public static final String PIANO_SHOW_NOTE = "PIANO_SHOW_NOTE";
    public static final String IS_RATED = "IS_RATED";
    public static final String IS_BACK_FROM_INSTRUMENT = "IS_BACK_FROM_INSTRUMENT";
    public static final String IS_BACK = "IS_BACK";
    public static final String STYLE_DRUM = "STYLE_DRUM";
    public static final String STYLE_PIANO = "STYLE_PIANO";
    public static final String PIANO_KEY_SIZE = "PIANO_KEY_SIZE";

    public static void init(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
    }
    private static SharedPreferences.Editor editor() {
        return sharedPreferences.edit();
    }

    public static void putBoolean(String key, boolean value) {
        editor().putBoolean(key, value).apply();
    }

    public static void putString(String key, String value) {
        editor().putString(key, value).apply();
    }

    public static void putInt(String key, int value) {
        editor().putInt(key, value).apply();
    }

    public static void putLong(String key, long value) {
        editor().putLong(key, value).apply();
    }

    public static boolean getBoolean(String key, boolean defaultvalue) {
        return sharedPreferences.getBoolean(key, defaultvalue);
    }

    public static String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public static int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }
    public static void setInt(String key, int value) {
        editor().putInt(key, value).apply();
    }

    public static long getLong(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }
    public static void setStyleDrum(int key){
        editor().putInt(STYLE_DRUM, key).apply();
    }
    public static int getStyleDrum() {
        return sharedPreferences.getInt(STYLE_DRUM, 0);
    }
}
