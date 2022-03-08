package com.marchenaya.data.manager.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marchenaya.data.entity.db.CharacterDBEntity

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCharacterList(characterList: List<CharacterDBEntity>)

    @Query("SELECT * FROM character WHERE comicId = :comicId")
    suspend fun getCharactersByComicId(comicId: Int): List<CharacterDBEntity>

    @Delete
    suspend fun removeCharacterList(characterList: List<CharacterDBEntity>)

}