package com.fw.vlad.android.domain.model

// Setting up the Business Data for the Domain Layer
data class Project(val id: String, val name: String, val fullName: String,
                   val starCount: String, val dateCreated: String,
                   val ownerName: String, val ownerAvatar: String,
                   val isBookmarked: Boolean = false)