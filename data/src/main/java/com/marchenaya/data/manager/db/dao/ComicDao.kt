package com.marchenaya.data.manager.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marchenaya.data.model.Comic

//Impossible to use clean architecture with Paging, usually it use a ComicDBEntity
@Dao
interface ComicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveComic(comic: Comic)

    @Query("SELECT * FROM comic WHERE id = :comicId")
    suspend fun getComicById(comicId: Int): Comic

    @Query("SELECT * FROM comic WHERE title LIKE (:title) ORDER BY title ASC")
    fun getComicsByTitle(title: String): PagingSource<Int, Comic>

    @Delete
    suspend fun removeComic(comic: Comic)

}