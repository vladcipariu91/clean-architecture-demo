package com.fw.vlad.android.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fw.vlad.android.domain.interactor.bookmark.GetBookmarkedProjects
import com.fw.vlad.android.domain.model.Project
import com.fw.vlad.android.presentation.mapper.ProjectViewMapper
import com.fw.vlad.android.presentation.model.ProjectView
import com.fw.vlad.android.presentation.state.Resource
import com.fw.vlad.android.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class BrowseBookmarkedProjectsViewModel @Inject constructor(
        private val getBookmarkedProjects: GetBookmarkedProjects,
        private val mapper: ProjectViewMapper) : ViewModel() {

    private val liveData = MutableLiveData<Resource<List<ProjectView>>>()

    override fun onCleared() {
        getBookmarkedProjects.dispose()
        super.onCleared()
    }

    fun getProjects(): LiveData<Resource<List<ProjectView>>> {
        return liveData
    }

    fun fetchProjects() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        getBookmarkedProjects.execute(ProjectsSubscriber())
    }

    inner class ProjectsSubscriber : DisposableObserver<List<Project>>() {
        override fun onComplete() {}

        override fun onNext(projects: List<Project>) {
            liveData.postValue(Resource(ResourceState.SUCCESS, projects.map {
                mapper.mapToView(it)
            }, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }
    }
}