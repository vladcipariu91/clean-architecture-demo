package com.fw.vlad.android.mobile_ui.injection.module

import android.app.Application
import com.fw.vlad.android.cache.ProjectsCacheImpl
import com.fw.vlad.android.cache.db.ProjectsDatabase
import com.fw.vlad.android.data.repository.ProjectsCache
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class CacheModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDatabase(application: Application): ProjectsDatabase {
            return ProjectsDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindProjectsCache(projectsCache: ProjectsCacheImpl): ProjectsCache
}