package com.marchenaya.data.manager.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marchenaya.data.entity.db.UrlDBEntity

@Dao
interface UrlDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUrlList(comicUrlList: List<UrlDBEntity>)

    @Query("SELECT * FROM url WHERE comicId = :comicId")
    suspend fun getUrlsByComicId(comicId: Int): List<UrlDBEntity>

    @Delete
    suspend fun removeUrlList(comicUrl: List<UrlDBEntity>)

}