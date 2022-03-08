package com.marchenaya.data.manager.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marchenaya.data.entity.db.CreatorDBEntity

@Dao
interface CreatorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCreatorList(creatorList: List<CreatorDBEntity>)

    @Query("SELECT * FROM creator WHERE comicId = :comicId")
    suspend fun getCreatorsByComicId(comicId: Int): List<CreatorDBEntity>

    @Delete
    suspend fun removeCreatorList(creatorList: List<CreatorDBEntity>)

}