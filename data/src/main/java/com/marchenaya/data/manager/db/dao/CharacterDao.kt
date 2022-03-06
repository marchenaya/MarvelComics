package com.marchenaya.data.manager.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marchenaya.data.model.Character

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCharacterList(characterList: List<Character>)

    @Query("SELECT * FROM character WHERE id = :comicId")
    suspend fun getCharactersByComicId(comicId: Int): List<Character>

    @Delete
    suspend fun removeCharacterList(characterList: List<Character>)

}