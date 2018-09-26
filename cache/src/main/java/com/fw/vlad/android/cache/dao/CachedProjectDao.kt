package com.fw.vlad.android.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fw.vlad.android.cache.db.ProjectConstants.DELETE_PROJECTS
import com.fw.vlad.android.cache.db.ProjectConstants.QUERY_BOOKMARKED_PROJECTS
import com.fw.vlad.android.cache.db.ProjectConstants.QUERY_PROJECTS
import com.fw.vlad.android.cache.db.ProjectConstants.QUERY_UPDATE_BOOKMARK_STATUS
import com.fw.vlad.android.cache.model.CachedProject
import io.reactivex.Flowable

@Dao
abstract class CachedProjectDao {

    @Query(QUERY_PROJECTS)
    abstract fun getProjects(): Flowable<List<CachedProject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertProjects(projects: List<CachedProject>)

    @Query(DELETE_PROJECTS)
    abstract fun deleteProjects()

    @Query(QUERY_BOOKMARKED_PROJECTS)
    abstract fun getBookmarkerProjects(): Flowable<List<CachedProject>>

    @Query(QUERY_UPDATE_BOOKMARK_STATUS)
    abstract fun setBookmarkedStatus(isBookmarked: Boolean,
                                     projectId: String)
}