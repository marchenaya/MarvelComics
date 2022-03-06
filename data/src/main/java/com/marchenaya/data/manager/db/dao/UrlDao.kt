package com.marchenaya.data.manager.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marchenaya.data.model.ComicUrl

@Dao
interface UrlDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUrlList(comicUrlList: List<ComicUrl>)

    @Query("SELECT * FROM comic_url WHERE comicId = :comicId")
    suspend fun getUrlsByComicId(comicId: Int): List<ComicUrl>

    @Delete
    suspend fun removeUrlList(comicUrl: List<ComicUrl>)

}