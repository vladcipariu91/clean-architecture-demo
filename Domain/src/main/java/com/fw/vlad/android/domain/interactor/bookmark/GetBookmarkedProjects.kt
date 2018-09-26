package com.fw.vlad.android.domain.interactor.bookmark

import com.fw.vlad.android.domain.executor.PostExecutionThread
import com.fw.vlad.android.domain.interactor.ObservableUseCase
import com.fw.vlad.android.domain.model.Project
import com.fw.vlad.android.domain.repository.ProjectsRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetBookmarkedProjects @Inject constructor(
        private val projectsRepository: ProjectsRepository,
        postExecutionThread: PostExecutionThread)
    : ObservableUseCase<List<Project>, Nothing?>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return projectsRepository.getBookmarkedProjects()
    }
}