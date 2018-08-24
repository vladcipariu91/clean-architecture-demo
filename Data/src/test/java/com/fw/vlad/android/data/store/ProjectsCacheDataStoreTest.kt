package com.fw.vlad.android.data.store

import com.fw.vlad.android.data.model.ProjectEntity
import com.fw.vlad.android.data.repository.ProjectsCache
import com.fw.vlad.android.data.test.factory.DataFactory
import com.fw.vlad.android.data.test.factory.ProjectFactory
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class ProjectsCacheDataStoreTest {

    @Mock
    lateinit var cache: ProjectsCache
    lateinit var store: ProjectsCacheDataStore

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        store = ProjectsCacheDataStore(cache)
    }

    @Test
    fun getProjectsCompletes() {
        stubProjectCacheGetProjects(Observable.just(
                listOf(ProjectFactory.makeProjectEntity())))

        val testObserver = store.getProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsReturnsData() {
        val projects = listOf(ProjectFactory.makeProjectEntity())
        stubProjectCacheGetProjects(Observable.just(projects))

        val testObserver = store.getProjects().test()
        testObserver.assertValue(projects)
    }

    @Test
    fun getProjectsCallsTheCache() {
        stubProjectCacheGetProjects(Observable.just(
                listOf(ProjectFactory.makeProjectEntity())))
        store.getProjects().test()
        verify(cache).getProjects()
    }

    @Test
    fun saveProjectsCompletes() {
        stubProjectCacheSaveProject(Completable.complete())
        stubProjectCacheSetLastCacheTime(Completable.complete())

        val testObserver = store.saveProjects(listOf(ProjectFactory.makeProjectEntity())).test()
        testObserver.assertComplete()
    }

    @Test
    fun saveProjectsCallsTheCache() {
        stubProjectCacheSaveProject(Completable.complete())
        stubProjectCacheSetLastCacheTime(Completable.complete())

        val projectEntities = listOf(ProjectFactory.makeProjectEntity())
        store.saveProjects(projectEntities).test()
        verify(cache).saveProject(projectEntities)
        verify(cache).setLastCacheTime(anyLong())
    }

    @Test
    fun clearProjectsCompletes() {
        stubProjectCacheClearProjects(Completable.complete())

        val testObserver = store.clearProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun clearProjectsCallTheCache() {
        stubProjectCacheClearProjects(Completable.complete())

        store.clearProjects().test()
        verify(cache).clearProjects()
    }

    @Test
    fun getBookmarkedProjectsCompletes() {
        stubProjectCacheGetBookmarkedProjects(Observable.just(
                listOf(ProjectFactory.makeProjectEntity())))

        val testObserver = store.getBookmarkedProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBookmarkedProjectsReturnsData() {
        val projects = listOf(ProjectFactory.makeProjectEntity())
        stubProjectCacheGetBookmarkedProjects(Observable.just(projects))

        val testObserver = store.getBookmarkedProjects().test()
        testObserver.assertValue(projects)
    }

    @Test
    fun getBookmarkedProjectsCallsTheCache() {
        stubProjectCacheGetBookmarkedProjects(Observable.just(
                listOf(ProjectFactory.makeProjectEntity())))
        store.getBookmarkedProjects().test()
        verify(cache).getBookmarkedProjects()
    }

    @Test
    fun setProjectAsBookmarkedCompletes() {
        stubProjectCacheSetProjectAsBookmarked(Completable.complete())

        val testObserver = store.setProjectAsBookmarked(DataFactory.randomString()).test()
        testObserver.assertComplete()
    }

    @Test
    fun setProjectAsBookmarkedCallsTheCache() {
        stubProjectCacheSetProjectAsBookmarked(Completable.complete())

        store.setProjectAsBookmarked(DataFactory.randomString()).test()
        verify(cache).setProjectAsBookmarked(anyString())
    }

    @Test
    fun setProjectAsNotBookmarkedCompletes() {
        stubProjectCacheSetProjectAsNotBookmarked(Completable.complete())

        val testObserver = store.setProjectAsNotBookmarked(DataFactory.randomString()).test()
        testObserver.assertComplete()
    }

    @Test
    fun setProjectAsNotBookmarkedCallsTheCache() {
        stubProjectCacheSetProjectAsNotBookmarked(Completable.complete())

        store.setProjectAsNotBookmarked(DataFactory.randomString()).test()
        verify(cache).setProjectAsNotBookmarked(anyString())
    }

    private fun stubProjectCacheGetProjects(observable: Observable<List<ProjectEntity>>) {
        `when`(cache.getProjects())
                .thenReturn(observable)
    }

    private fun stubProjectCacheSaveProject(completable: Completable) {
        `when`(cache.saveProject(anyList()))
                .thenReturn(completable)
    }

    private fun stubProjectCacheSetLastCacheTime(completable: Completable) {
        `when`(cache.setLastCacheTime(anyLong()))
                .thenReturn(completable)
    }

    private fun stubProjectCacheClearProjects(completable: Completable) {
        `when`(cache.clearProjects())
                .thenReturn(completable)
    }

    private fun stubProjectCacheGetBookmarkedProjects(observable: Observable<List<ProjectEntity>>) {
        `when`(cache.getBookmarkedProjects())
                .thenReturn(observable)
    }

    private fun stubProjectCacheSetProjectAsBookmarked(completable: Completable) {
        `when`(cache.setProjectAsBookmarked(anyString()))
                .thenReturn(completable)
    }

    private fun stubProjectCacheSetProjectAsNotBookmarked(completable: Completable) {
        `when`(cache.setProjectAsNotBookmarked(anyString()))
                .thenReturn(completable)
    }

}