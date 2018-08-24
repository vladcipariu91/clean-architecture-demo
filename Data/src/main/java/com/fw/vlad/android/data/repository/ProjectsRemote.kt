package com.fw.vlad.android.data.repository

import com.fw.vlad.android.data.model.ProjectEntity
import io.reactivex.Observable

interface ProjectsRemote {

    fun getProjects(): Observable<List<ProjectEntity>>
}