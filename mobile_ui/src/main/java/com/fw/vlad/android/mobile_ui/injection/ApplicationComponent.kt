package com.fw.vlad.android.mobile_ui.injection

import android.app.Application
import com.fw.vlad.android.mobile_ui.GitHubTrendingApplication
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: GitHubTrendingApplication)
}