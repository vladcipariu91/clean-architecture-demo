package com.fw.vlad.android.mobile_ui.injection.module

import com.fw.vlad.android.domain.executor.PostExecutionThread
import com.fw.vlad.android.mobile_ui.BrowseActivity
import com.fw.vlad.android.mobile_ui.UiThread
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesBrowseActivity(): BrowseActivity
}