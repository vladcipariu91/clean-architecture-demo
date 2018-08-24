package com.fw.vlad.android.remote.model

import com.google.gson.annotations.SerializedName

data class OwnerModel(@SerializedName("login") val name: String,
                      @SerializedName("avatar_url") val avatar: String)