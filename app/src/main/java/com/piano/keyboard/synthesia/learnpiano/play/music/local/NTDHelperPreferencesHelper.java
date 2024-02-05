package com.piano.keyboard.synthesia.learnpiano.play.music.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NTDHelperPreferencesHelper {
    private static SharedPreferences sharedPreferences;
    private static final String NAME = "MyPref";
    public static final String LANGUAGE = "LANGUAGE";

    public static void init(Context mContext) {
        sharedPreferences = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }
    public static void setLanguage(String language) {
        sharedPreferences.edit().putString(LANGUAGE, language).apply();
    }

    public static String getLanguage() {
        Locale.getDefault().getDisplayLanguage();
        String lang;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            lang = Resources.getSystem().getConfiguration().getLocales().get(0).getLanguage();
        } else {
            lang = Resources.getSystem().getConfiguration().locale.getLanguage();
        }
        if (getListLanguage().contains(lang)) {
            return sharedPreferences.getString(LANGUAGE, "en");
        } else {
            return sharedPreferences.getString(LANGUAGE, lang);
        }
    }

    private static List<String> getListLanguage() {
        ArrayList<String> listLanguage = new ArrayList<>();
        listLanguage.add("en");
        listLanguage.add("pt");
        listLanguage.add("fr");
        listLanguage.add("es");
        listLanguage.add("hi");
        return listLanguage;
    }
}
