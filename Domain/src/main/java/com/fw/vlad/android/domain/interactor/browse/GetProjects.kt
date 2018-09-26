package com.fw.vlad.android.domain.interactor.browse

import com.fw.vlad.android.domain.executor.PostExecutionThread
import com.fw.vlad.android.domain.interactor.ObservableUseCase
import com.fw.vlad.android.domain.model.Project
import com.fw.vlad.android.domain.repository.ProjectsRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 *  There's no mapping of models here as the domain layer is our central layer meaning
 *  no reference to outside layers and therefore there is no need for these mapping operations
 */

// Implementing the Get Projects use case
class GetProjects @Inject constructor(
        private val projectsRepository: ProjectsRepository,
        postExecutionThread: PostExecutionThread) :
        ObservableUseCase<List<Project>, Nothing?>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return projectsRepository.getProjects()
    }
}