package com.fw.vlad.android.presentation.mapper

import com.fw.vlad.android.domain.model.Project
import com.fw.vlad.android.presentation.model.ProjectView
import javax.inject.Inject

class ProjectViewMapper @Inject constructor(): Mapper<ProjectView, Project> {

    override fun mapToView(type: Project): ProjectView {
        return ProjectView(type.id,
                type.name,
                type.fullName,
                type.starCount,
                type.dateCreated,
                type.ownerName,
                type.ownerAvatar,
                type.isBookmarked)

    }
}