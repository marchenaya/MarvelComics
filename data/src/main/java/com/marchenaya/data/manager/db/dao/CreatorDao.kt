package com.marchenaya.data.manager.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marchenaya.data.model.Creator

@Dao
interface CreatorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCreatorList(creatorList: List<Creator>)

    @Query("SELECT * FROM creator WHERE comicId = :comicId")
    suspend fun getCreatorsByComicId(comicId: Int): List<Creator>

    @Delete
    suspend fun removeCreatorList(creatorList: List<Creator>)

}