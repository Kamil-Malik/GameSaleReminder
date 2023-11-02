package com.lelestacia.gamediscountreminder.application

import android.app.Application
import com.lelestacia.gamediscountreminder.BuildConfig
import com.lelestacia.gamediscountreminder.util.CrashReportingTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class GameDiscountApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }
}