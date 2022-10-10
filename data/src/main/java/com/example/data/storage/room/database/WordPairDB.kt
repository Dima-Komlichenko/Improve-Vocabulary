package com.example.data.storage.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.storage.models.OnStudyWordPair
import com.example.data.storage.models.PendingWordPair
import com.example.data.storage.models.StudiedWordPair
import com.example.data.storage.room.dao.OnStudyWordPairDao
import com.example.data.storage.room.dao.PendingWordPairDao
import com.example.data.storage.room.dao.StudiedWordPairDao


@Database(entities = [OnStudyWordPair::class, PendingWordPair::class, StudiedWordPair::class], version = 1, exportSchema = false)
abstract class WordPairDB: RoomDatabase() {

    abstract fun pendingWordPairDao(): PendingWordPairDao
    abstract fun onStudyWordPairDao(): OnStudyWordPairDao
    abstract fun studiedWordPairDao(): StudiedWordPairDao

    companion object {
        @Volatile
        private var INSTANCE: WordPairDB? = null

        fun getDB(context: Context): WordPairDB {
            val tempInstance = INSTANCE
            if(tempInstance != null) return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordPairDB::class.java,
                    "word_pairs_db"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
