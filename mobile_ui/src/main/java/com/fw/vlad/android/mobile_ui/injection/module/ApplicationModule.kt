package com.fw.vlad.android.mobile_ui.injection.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
abstract class AdpplicationModule {

    @Binds
    abstract fun bindContext(application: Application) : Context
}