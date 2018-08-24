package com.fw.vlad.android.domain.repository

import com.fw.vlad.android.domain.model.Project
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * The Domain layer is the central point of our Android Applications architecture.
 * In this lesson, we’re going to be creating the repository interface which will define the
 * data access methods to be implemented by the outer layers of our Clean Architecture.
 */

// Setting up the data access interface for the Domain Layer
interface ProjectsRepository {

    fun getProjects(): Observable<List<Project>>

    fun bookmarkProject(projectId: String): Completable

    fun unbookmarkProject(projectId: String): Completable

    fun getBookmarkedProjects(): Observable<List<Project>>
}