package com.marchenaya.data.manager.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.marchenaya.data.manager.db.dao.CharacterDao
import com.marchenaya.data.manager.db.dao.ComicDao
import com.marchenaya.data.manager.db.dao.CreatorDao
import com.marchenaya.data.manager.db.dao.KeyDao
import com.marchenaya.data.manager.db.dao.UrlDao
import com.marchenaya.data.model.Character
import com.marchenaya.data.model.Comic
import com.marchenaya.data.model.ComicKey
import com.marchenaya.data.model.ComicUrl
import com.marchenaya.data.model.Creator

@Database(
    entities = [Comic::class, Character::class, Creator::class, ComicUrl::class, ComicKey::class],
    version = 1,
    exportSchema = false
)
abstract class MarvelDatabase : RoomDatabase() {

    abstract fun comicDao(): ComicDao

    abstract fun characterDao(): CharacterDao

    abstract fun creatorDao(): CreatorDao

    abstract fun urlDao(): UrlDao

    abstract fun keyDao(): KeyDao

    companion object {
        private const val DATABASE_NAME = "marvel_database"

        private var INSTANCE: MarvelDatabase? = null

        fun getDatabase(context: Context): MarvelDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    MarvelDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }.also { INSTANCE = it }
        }
    }

}