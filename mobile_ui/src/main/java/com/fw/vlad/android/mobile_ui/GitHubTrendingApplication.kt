package com.fw.vlad.android.mobile_ui

import android.app.Application
import timber.log.Timber

class GitHubTrendingApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupTimber()
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }
}