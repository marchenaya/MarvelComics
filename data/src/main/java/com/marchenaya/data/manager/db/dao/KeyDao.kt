package com.marchenaya.data.manager.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marchenaya.data.model.ComicKey

@Dao
interface KeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveKey(comicKey: ComicKey)

    @Query("SELECT * FROM comic_key WHERE comicId = :comicId")
    suspend fun getKeyByComicId(comicId: Int): ComicKey

    @Delete
    suspend fun removeKey(comicKey: ComicKey)

}