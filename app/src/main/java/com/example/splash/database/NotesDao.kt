package com.example.splash.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.splash.models.NotesEntity

@Dao
interface NotesDao {
    //suspend insert function for saving an entry
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notesEntity: NotesEntity)

    //  suspend update function for updating an existing entry
    @Update
    suspend fun update(notesEntity: NotesEntity)

    // suspend delete function for deleting an existing entry
    @Delete
    suspend fun delete(notesEntity: NotesEntity)

    //function to read all notes, this returns a Livedata
    @Query("Select * from `notes-table` order by id DESC")
    fun getAllNotes(): LiveData<List<NotesEntity>>

}
