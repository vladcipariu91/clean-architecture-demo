package com.fw.vlad.android.remote.mapper

import com.fw.vlad.android.data.model.ProjectEntity
import com.fw.vlad.android.remote.model.ProjectModel
import javax.inject.Inject

class ProjectsResponseModelMapper @Inject constructor() : ModelMapper<ProjectModel, ProjectEntity> {

    override fun mapFromModel(model: ProjectModel): ProjectEntity {
        return ProjectEntity(
                model.id,
                model.name,
                model.fullName,
                model.starCount,
                model.dateCreated,
                model.owner.name,
                model.owner.avatar)
    }
}