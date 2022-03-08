package com.marchenaya.data.manager.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marchenaya.data.entity.db.ComicDBEntity

@Dao
interface ComicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveComic(comic: ComicDBEntity)

    @Query("SELECT * FROM comic WHERE id = :comicId")
    suspend fun getComicById(comicId: Int): ComicDBEntity?

    @Query("SELECT * FROM comic WHERE title LIKE (:title) ORDER BY title ASC")
    fun getComicsByTitle(title: String): PagingSource<Int, ComicDBEntity>

    @Query("SELECT * FROM comic WHERE title LIKE (:title) ORDER BY title ASC")
    suspend fun getComicsByTitleList(title: String): List<ComicDBEntity>

    @Delete
    suspend fun removeComic(comic: ComicDBEntity)

}