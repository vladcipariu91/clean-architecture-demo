package com.fw.vlad.android.data.store

import com.fw.vlad.android.data.model.ProjectEntity
import com.fw.vlad.android.data.repository.ProjectsRemote
import com.fw.vlad.android.data.test.factory.DataFactory
import com.fw.vlad.android.data.test.factory.ProjectFactory
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class ProjectsRemoteDataStoreTest {

    @Mock
    lateinit var remote: ProjectsRemote
    lateinit var store: ProjectsRemoteDataStore

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        store = ProjectsRemoteDataStore(remote)
    }

    @Test
    fun getProjectsCompletes() {
        stubProjectsRemoteGetProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))

        val testObserver = store.getProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsReturnsData() {
        val projectEntities = listOf(ProjectFactory.makeProjectEntity())
        stubProjectsRemoteGetProjects(Observable.just(projectEntities))

        val testObserver = store.getProjects().test()
        testObserver.assertValue(projectEntities)
    }

    @Test
    fun getProjectsCallsTheRemote() {
        stubProjectsRemoteGetProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))

        store.getProjects().test()
        verify(remote).getProjects()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun saveProjectsThrowsException() {
        val projects = listOf(ProjectFactory.makeProjectEntity())
        store.saveProjects(projects).test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun clearProjectsThrowsException() {
        store.clearProjects().test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun getBookmarkedProjectsThrowsException() {
        store.getBookmarkedProjects().test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun setProjectAsBookmarkedThrowsException() {
        store.setProjectAsBookmarked(DataFactory.randomString()).test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun setProjectAsNotBookmarkedThrowsException() {
        store.setProjectAsNotBookmarked(DataFactory.randomString()).test()
    }

    private fun stubProjectsRemoteGetProjects(observable: Observable<List<ProjectEntity>>) {
        `when`(remote.getProjects())
                .thenReturn(observable)
    }
}