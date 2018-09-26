package com.fw.vlad.android.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fw.vlad.android.cache.db.ProjectConstants

@Entity(tableName = ProjectConstants.TABLE_NAME)
data class CachedProject(@PrimaryKey var id: String,
                         var name: String,
                         var fullName: String,
                         var starCount: String,
                         var dateCreated: String,
                         var ownerName: String,
                         var ownerAvatar: String,
                         var isBookmarked: Boolean)