package com.fw.vlad.android.remote

import com.fw.vlad.android.data.model.ProjectEntity
import com.fw.vlad.android.data.repository.ProjectsRemote
import com.fw.vlad.android.remote.mapper.ProjectsResponseModelMapper
import com.fw.vlad.android.remote.service.GithubTrendingService
import io.reactivex.Observable
import javax.inject.Inject

class ProjectsRemoteImpl @Inject constructor(
        private val service: GithubTrendingService,
        private val mapper: ProjectsResponseModelMapper) : ProjectsRemote {

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return service.searchRepositories("language:kotlin", "start", "desc")
                .map { projectResponseModel ->
                    projectResponseModel.items.map { projectModel ->
                        mapper.mapFromModel(projectModel)
                    }
                }
    }
}