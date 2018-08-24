package com.fw.vlad.android.remote.model

import com.google.gson.annotations.SerializedName

class ProjectModel(@SerializedName("id") val id: String,
                   @SerializedName("name") val name: String,
                   @SerializedName("fullName") val fullName: String,
                   @SerializedName("starCount") val starCount: String,
                   @SerializedName("dateCreated") val dateCreated: String,
                   @SerializedName("owner") val owner: OwnerModel)