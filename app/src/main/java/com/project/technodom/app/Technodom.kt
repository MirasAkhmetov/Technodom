package com.project.technodom.app

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import com.pixplicity.easyprefs.library.Prefs
import com.project.technodom.global.utils.LocaleManager
import com.project.technodom.global.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class Technodom :Application() {
    companion object{
        var localeManager: LocaleManager? = null
    }

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@Technodom)
            modules(appModule)
        }

        initPrefs()
        Timber.plant(Timber.DebugTree())
    }

    private fun initPrefs() {
        Prefs.Builder()
            .setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        localeManager!!.setLocale(this)
    }

    override fun attachBaseContext(base: Context) {
        localeManager = LocaleManager(base)
        super.attachBaseContext(localeManager!!.setLocale(base))
    }
}