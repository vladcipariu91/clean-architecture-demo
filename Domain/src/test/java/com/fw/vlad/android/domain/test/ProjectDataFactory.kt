package com.fw.vlad.android.domain.test

import com.fw.vlad.android.domain.model.Project
import java.util.*

object ProjectDataFactory {

    fun randomUuid(): String {
        return UUID.randomUUID().toString()
    }

    private fun randomBoolean(): Boolean {
        return Math.random() < 0.5
    }

    fun makeProject() = Project(randomUuid(), randomUuid(), randomUuid(),
            randomUuid(), randomUuid(), randomUuid(),
            randomUuid(), randomBoolean())

    fun makeProjectList(count: Int): List<Project> {
        val projects = mutableListOf<Project>()
        repeat(count) {
            projects.add(makeProject())
        }

        return projects
    }
}