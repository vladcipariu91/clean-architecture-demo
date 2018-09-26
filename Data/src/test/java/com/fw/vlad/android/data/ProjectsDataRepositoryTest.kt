package com.fw.vlad.android.data

import com.fw.vlad.android.data.mapper.ProjectMapper
import com.fw.vlad.android.data.model.ProjectEntity
import com.fw.vlad.android.data.repository.ProjectsCache
import com.fw.vlad.android.data.repository.ProjectsDataStore
import com.fw.vlad.android.data.store.ProjectsDataStoreFactory
import com.fw.vlad.android.data.test.factory.DataFactory
import com.fw.vlad.android.data.test.factory.ProjectFactory
import com.fw.vlad.android.domain.model.Project
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.*
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class ProjectsDataRepositoryTest {

    @Mock
    lateinit var mapper: ProjectMapper
    @Mock
    lateinit var factory: ProjectsDataStoreFactory
    @Mock
    lateinit var store: ProjectsDataStore
    @Mock
    lateinit var cache: ProjectsCache
    private lateinit var repository: ProjectsDataRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = ProjectsDataRepository(mapper, cache, factory)

        stubFactoryGetDataStore()
        stubFactoryGetCacheDataStore()
        stubIsCacheExpired(Single.just(false))
        stubAreProjectsCached(Single.just(false))
        stubSaveProjects(Completable.complete())
    }

    @Test
    fun getProjectsCompletes() {
        stubGetProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        stubMapper(ProjectFactory.makeProject(), ProjectFactory.makeProjectEntity())

        val testObserver = repository.getProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsReturnsData() {
        val projectEntity = ProjectFactory.makeProjectEntity()
        val project = ProjectFactory.makeProject()
        stubGetProjects(Observable.just(listOf(projectEntity)))
        stubMapper(project, projectEntity)

        val testObserver = repository.getProjects().test()
        testObserver.assertValue(listOf(project))
    }

    @Test
    fun getBookmarkedProjectsCompletes() {
        stubGetBookmarkedProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        stubMapper(ProjectFactory.makeProject(), ProjectFactory.makeProjectEntity())

        val testObserver = repository.getBookmarkedProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBookmarkedProjectsReturnsData() {
        val projectEntity = ProjectFactory.makeProjectEntity()
        val project = ProjectFactory.makeProject()
        stubGetBookmarkedProjects(Observable.just(listOf(projectEntity)))
        stubMapper(project, projectEntity)

        val testObserver = repository.getBookmarkedProjects().test()
        testObserver.assertValue(listOf(project))
    }

    @Test
    fun bookmarkProjectCompletes() {
        stubBookmarkProject(Completable.complete())

        val testObserver = repository.bookmarkProject(DataFactory.randomString()).test()
        testObserver.assertComplete()
    }

    @Test
    fun unbookmarkProjectCompletes() {
        stubUnBookmarkProject(Completable.complete())

        val testObserver = repository.unbookmarkProject(DataFactory.randomString()).test()
        testObserver.assertComplete()
    }

    private fun stubBookmarkProject(completable: Completable) {
        `when`(store.setProjectAsBookmarked(anyString()))
                .thenReturn(completable)
    }

    private fun stubUnBookmarkProject(completable: Completable) {
        `when`(store.setProjectAsNotBookmarked(anyString()))
                .thenReturn(completable)
    }

    private fun stubIsCacheExpired(single: Single<Boolean>) {
        `when`(cache.isProjectsCacheExpired())
                .thenReturn(single)
    }

    private fun stubAreProjectsCached(single: Single<Boolean>) {
        `when`(cache.areProjectsCached())
                .thenReturn(single)
    }

    private fun stubMapper(model: Project, entity: ProjectEntity) {
        `when`(mapper.mapFromEntity(entity))
                .thenReturn(model)
    }

    private fun stubGetProjects(observable: Observable<List<ProjectEntity>>) {
        `when`(store.getProjects())
                .thenReturn(observable)
    }

    private fun stubGetBookmarkedProjects(observable: Observable<List<ProjectEntity>>) {
        `when`(store.getBookmarkedProjects())
                .thenReturn(observable)
    }

    private fun stubFactoryGetDataStore() {
        `when`(factory.getDataStore(anyBoolean(), anyBoolean()))
                .thenReturn(store)
    }

    private fun stubFactoryGetCacheDataStore() {
        `when`(factory.getCacheDataStore())
                .thenReturn(store)
    }

    private fun stubSaveProjects(completable: Completable) {
        `when`(store.saveProjects(anyList()))
                .thenReturn(completable)
    }
}