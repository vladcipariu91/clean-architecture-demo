package com.fw.vlad.android.mobile_ui.browse

interface ProjectListener {

    fun onBookmarkedProjectClicked(projectId: String)

    fun onProjectClicked(projectId: String)
}