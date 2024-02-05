package com.ntd.appid1.appid2.appid3.utils

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import java.util.Locale

object NTDOtherUtils {
    private const val NAME_THEME_SHARED = "NAME_THEME_SHARED"
    const val KEY_THEME_SHARE = "KEY_THEME_SHARE"
    private var index: Int = 0
    private lateinit var sharedPreferences: SharedPreferences


    fun init(mContext: Context) {
        sharedPreferences = mContext.getSharedPreferences(NAME_THEME_SHARED, Context.MODE_PRIVATE)
    }

    private fun editor(): SharedPreferences.Editor {
        return sharedPreferences.edit()
    }

    fun getIndexTheme(): Int = index

    fun saveThem(value: Int) {
        editor().putInt(KEY_THEME_SHARE, value).apply()
    }


    fun setLocale(context: Context, language: String) {
        if (language.isEmpty()) {
            val config = Configuration()
            val locale = Locale.getDefault()
            Locale.setDefault(locale)
            config.locale = locale
            context.resources.updateConfiguration(config, context.resources.displayMetrics)
        } else {
            changeLang(language, context)
        }
    }

    private fun changeLang(lang: String, context: Context) {
        if (lang.equals("", ignoreCase = true)) return
        val myLocale = Locale(lang)
        Locale.setDefault(myLocale)
        val config = Configuration()
        config.locale = myLocale
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }
}