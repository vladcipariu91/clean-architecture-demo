package com.fw.vlad.android.remote

import com.fw.vlad.android.data.model.ProjectEntity
import com.fw.vlad.android.remote.mapper.ProjectsResponseModelMapper
import com.fw.vlad.android.remote.model.ProjectModel
import com.fw.vlad.android.remote.model.ProjectsResponseModel
import com.fw.vlad.android.remote.service.GithubTrendingService
import com.fw.vlad.android.remote.test.factory.ProjectDataFactory
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class ProjectsRemoteImplTest {

    @Mock
    private lateinit var mapper: ProjectsResponseModelMapper
    @Mock
    private lateinit var service: GithubTrendingService
    private lateinit var remote: ProjectsRemoteImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        remote = ProjectsRemoteImpl(service, mapper)
    }

    @Test
    fun getProjectCompletes() {
        stubGithubTrendingService(Observable.just(ProjectDataFactory.makeProjectResponse()))
        stubProjectsResponseModelMapperMapFromModel(
                ProjectDataFactory.makeProject(),
                ProjectDataFactory.makeProjectEntity())

        val testObserver = remote.getProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsCallsServer() {
        stubGithubTrendingService(Observable.just(ProjectDataFactory.makeProjectResponse()))
        stubProjectsResponseModelMapperMapFromModel(
                ProjectDataFactory.makeProject(),
                ProjectDataFactory.makeProjectEntity())

        remote.getProjects().test()
        verify(service).searchRepositories(anyString(), anyString(), anyString())
    }

    @Test
    fun getProjectsReturnsData() {
        val response = ProjectDataFactory.makeProjectResponse()

        stubGithubTrendingService(Observable.just(response))
        stubProjectsResponseModelMapperMapFromModel(
                ProjectDataFactory.makeProject(),
                ProjectDataFactory.makeProjectEntity())

        val entities = response.items.map { mapper.mapFromModel(it) }
        val testObserver = remote.getProjects().test()
        testObserver.assertValue(entities)
    }

    @Test
    fun getProjectsCallsServerWithCorrectParams() {
        stubGithubTrendingService(Observable.just(ProjectDataFactory.makeProjectResponse()))
        stubProjectsResponseModelMapperMapFromModel(
                ProjectDataFactory.makeProject(),
                ProjectDataFactory.makeProjectEntity())

        remote.getProjects().test()
        verify(service).searchRepositories("language:kotlin", "start", "desc")
    }

    private fun stubGithubTrendingService(observable: Observable<ProjectsResponseModel>) {
        `when`(service.searchRepositories(anyString(), anyString(), anyString()))
                .thenReturn(observable)
    }

    private fun stubProjectsResponseModelMapperMapFromModel(model: ProjectModel,
                                                            entity: ProjectEntity) {
        `when`(mapper.mapFromModel(model))
                .thenReturn(entity)
    }
}