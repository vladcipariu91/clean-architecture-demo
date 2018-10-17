package com.fw.vlad.android.data

import com.fw.vlad.android.data.mapper.ProjectMapper
import com.fw.vlad.android.data.repository.ProjectsCache
import com.fw.vlad.android.data.store.ProjectsDataStoreFactory
import com.fw.vlad.android.domain.model.Project
import com.fw.vlad.android.domain.repository.ProjectsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

/**
 * In this lesson, we'll be implementing the central access point for data,
 * the projects data repository class, for our data layer.
 *
 * The projects data repository is used to orchestrate the flow of data between
 * the domain module and the remote / cache modules.
 */
class ProjectsDataRepository @Inject constructor(
        private val projectMapper: ProjectMapper,
        private val cache: ProjectsCache,
        private val factory: ProjectsDataStoreFactory
) : ProjectsRepository {

    override fun getProjects(): Observable<List<Project>> {
        return Observable.zip(cache.areProjectsCached().toObservable(),
                cache.isProjectsCacheExpired().toObservable(),
                BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
                    Pair(areCached, isExpired)
                })
                .flatMap {
                    factory.getDataStore(it.first, it.second)
                            .getProjects()
                }
                .flatMap {
                    factory.getCacheDataStore()
                            .saveProjects(it)
                            .andThen(Observable.just(it))
                }
                .map { projectEntities ->
                    projectEntities.map {
                        projectMapper.mapFromEntity(it)
                    }
                }
    }

    override fun bookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectAsBookmarked(projectId)
    }

    override fun unbookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectAsNotBookmarked(projectId)
    }

    override fun getBookmarkedProjects(): Observable<List<Project>> {
        return factory.getCacheDataStore().getBookmarkedProjects()
                .map {
                    it.map {
                        projectMapper.mapFromEntity(it)
                    }
                }
    }
}