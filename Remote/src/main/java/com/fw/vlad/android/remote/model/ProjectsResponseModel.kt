package com.fw.vlad.android.remote.model

import com.google.gson.annotations.SerializedName

data class ProjectsResponseModel(@SerializedName("items") val items: List<ProjectModel>)