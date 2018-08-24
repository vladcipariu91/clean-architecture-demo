package com.fw.vlad.android.remote.test.factory

import com.fw.vlad.android.data.model.ProjectEntity
import com.fw.vlad.android.remote.model.OwnerModel
import com.fw.vlad.android.remote.model.ProjectModel
import com.fw.vlad.android.remote.model.ProjectsResponseModel

object ProjectDataFactory {

    fun makeOwner(): OwnerModel {
        return OwnerModel(DataFactory.randomString(),
                DataFactory.randomString())
    }

    fun makeProject(): ProjectModel {
        return ProjectModel(DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                makeOwner())
    }

    fun makeProjectEntity(): ProjectEntity {
        return ProjectEntity(DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomBoolean())
    }

    fun makeProjectResponse(): ProjectsResponseModel {
        return ProjectsResponseModel(listOf(makeProject(), makeProject()))
    }
}