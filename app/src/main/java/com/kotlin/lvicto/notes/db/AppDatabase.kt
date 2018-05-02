package com.kotlin.lvicto.notes.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.kotlin.lvicto.notes.db.Note

/**
 * Main database
 */
@Database(entities = [(Note::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun notesDao() : NotesDao

    companion object {
        private lateinit var INSTANCE: AppDatabase
        private var isInitialized = false

        @JvmStatic
        fun getInMemoryDatabase(context: Context): AppDatabase {
            if(!isInitialized) {
                isInitialized = true
                INSTANCE = Room.inMemoryDatabaseBuilder(context.applicationContext, AppDatabase::class.java)
                        // To simplify the codelab, allow queries on the main thread.
                        // Don't do this on a real app! See PersistenceBasicSample for an example.
                        .allowMainThreadQueries()
                        .build()
            }
            return INSTANCE
        }

        @JvmStatic
        fun destroyInstance() {
            isInitialized = false
        }
    }
}