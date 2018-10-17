package com.fw.vlad.android.mobile_ui.injection.module

import com.fw.vlad.android.data.ProjectsDataRepository
import com.fw.vlad.android.domain.repository.ProjectsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataRepository(dataRepository: ProjectsDataRepository): ProjectsRepository
}