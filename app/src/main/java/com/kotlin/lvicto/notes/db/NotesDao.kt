package com.kotlin.lvicto.notes.db

import android.arch.persistence.room.*

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNote(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateNote(note: Note)

    @Query("DELETE from Note")
    fun deleteAllNotes()

    @Query("SELECT * from Note")
    fun findAllNotesSync(): List<Note>
}
