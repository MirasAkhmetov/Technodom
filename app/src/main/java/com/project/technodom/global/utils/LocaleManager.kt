package com.project.technodom.global.utils

import android.content.Context
import android.content.ContextWrapper
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import java.util.*


class LocaleManager(val context: Context?) {

    private val prefs: SharedPreferences? =
        context?.getSharedPreferences("com.project.technodom-preferences", ContextWrapper.MODE_PRIVATE)

    val language: String?
        get() = prefs?.getString("language", AppConstants.LANG_RU)

    fun setLocale(c: Context): Context {
        return updateResources(c, language)
    }

    fun setNewLocale(c: Context, language: String): Context {
        return updateResources(c, language)
    }

    private fun updateResources(
        context: Context,
        language: String?
    ): Context {
        var mContext : Context = context
        val locale = Locale(language)
        Locale.setDefault(locale)
        val res = context?.resources
        val config = Configuration(res?.configuration)
        if (isAtLeastVersion(Build.VERSION_CODES.JELLY_BEAN_MR1)) {
            config.setLocale(locale)
            mContext = context.createConfigurationContext(config)
            Log.i("SDA", "Asdasda=${language}")
        } else {
            config.locale = locale
            res?.updateConfiguration(config, res.displayMetrics)
        }
        config.setLayoutDirection(locale)
        return mContext
    }

    fun isAtLeastVersion(version: Int): Boolean {
        return Build.VERSION.SDK_INT >= version
    }


}