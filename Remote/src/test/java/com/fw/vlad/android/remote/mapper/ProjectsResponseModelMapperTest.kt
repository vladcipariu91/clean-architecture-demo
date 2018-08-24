package com.fw.vlad.android.remote.mapper

import com.fw.vlad.android.remote.test.factory.ProjectDataFactory
import org.junit.Assert.*
import org.junit.Test

class ProjectsResponseModelMapperTest {

    private val mapper = ProjectsResponseModelMapper()

    @Test
    fun mapFromModelMapsData() {
        val model = ProjectDataFactory.makeProject()
        val entity = mapper.mapFromModel(model)

        assertEquals(model.id, entity.id)
        assertEquals(model.name, entity.name)
        assertEquals(model.fullName, entity.fullName)
        assertEquals(model.starCount, entity.starCount)
        assertEquals(model.dateCreated, entity.dateCreated)
        assertEquals(model.owner.name, entity.ownerName)
        assertEquals(model.owner.avatar, entity.ownerAvatar)
    }
}