package com.fw.vlad.android.domain.interactor.bookmark

import com.fw.vlad.android.domain.executor.PostExecutionThread
import com.fw.vlad.android.domain.interactor.CompletableUseCase
import com.fw.vlad.android.domain.repository.ProjectsRepository
import io.reactivex.Completable
import javax.inject.Inject

// Implementing the Bookmark Project use case
class BookmarkProject @Inject constructor(
        private val projectsRepository: ProjectsRepository,
        postExecutionThread: PostExecutionThread)
    : CompletableUseCase<BookmarkProject.Params>(postExecutionThread) {

    override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can't be null")

        return projectsRepository.bookmarkProject(params.projectId)
    }

    data class Params(val projectId: String) {
        companion object {
            fun forProject(projectId: String): Params {
                return Params(projectId)
            }
        }
    }
}