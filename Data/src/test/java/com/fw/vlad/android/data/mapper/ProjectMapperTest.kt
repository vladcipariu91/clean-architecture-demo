package com.fw.vlad.android.data.mapper

import com.fw.vlad.android.data.model.ProjectEntity
import com.fw.vlad.android.data.test.factory.ProjectFactory
import com.fw.vlad.android.domain.model.Project
import org.junit.Assert.assertEquals
import org.junit.Test

class ProjectMapperTest {

    private val mapper = ProjectMapper()

    @Test
    fun mapFromEntityToData() {
        val entity = ProjectFactory.makeProjectEntity()
        val model = mapper.mapFromEntity(entity)
        assertEqualData(entity, model)
    }

    @Test
    fun mapFromDataToEntity() {
        val model = ProjectFactory.makeProject()
        val entity = mapper.mapToEntity(model)
        assertEqualData(entity, model)
    }

    private fun assertEqualData(entity: ProjectEntity, model: Project) {
        assertEquals(entity.id, model.id)
        assertEquals(entity.name, model.name)
        assertEquals(entity.fullName, model.fullName)
        assertEquals(entity.starCount, model.starCount)
        assertEquals(entity.dateCreated, model.dateCreated)
        assertEquals(entity.ownerName, model.ownerName)
        assertEquals(entity.ownerAvatar, model.ownerAvatar)
        assertEquals(entity.isBookmarked, model.isBookmarked)
    }
}