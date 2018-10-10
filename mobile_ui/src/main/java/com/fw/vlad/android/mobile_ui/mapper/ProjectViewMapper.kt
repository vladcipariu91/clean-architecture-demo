package com.fw.vlad.android.mobile_ui.mapper

import com.fw.vlad.android.mobile_ui.model.Project
import com.fw.vlad.android.presentation.model.ProjectView
import javax.inject.Inject

class ProjectViewMapper @Inject constructor() : ViewMapper<ProjectView, Project> {

    override fun mapToView(presentation: ProjectView): Project =
            Project(presentation.id, presentation.name,
                    presentation.fullName, presentation.starCount,
                    presentation.dateCreated, presentation.ownerName,
                    presentation.ownerAvatar, presentation.isBookmarked)
}