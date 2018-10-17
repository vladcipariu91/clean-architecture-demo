package com.fw.vlad.android.mobile_ui.injection.module

import android.os.Build
import com.fw.vlad.android.data.repository.ProjectsRemote
import com.fw.vlad.android.mobile_ui.BuildConfig
import com.fw.vlad.android.remote.ProjectsRemoteImpl
import com.fw.vlad.android.remote.service.GithubTrendingService
import com.fw.vlad.android.remote.service.GithubTrendingServiceFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesGithubService(): GithubTrendingService {
            return GithubTrendingServiceFactory.makeGithubTrendingService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindProjectsRemote(projectsRemote: ProjectsRemoteImpl): ProjectsRemote
}