package com.example.splash.notesRepository

import androidx.lifecycle.LiveData
import com.example.splash.database.NotesDao
import com.example.splash.models.NotesEntity

class NotesRepository ( private val notesDao: NotesDao){
    val allNotes:  LiveData<List<NotesEntity>> = notesDao.getAllNotes()

    suspend fun insert(notesEntity: NotesEntity){
        notesDao.insert(notesEntity)
    }
    suspend fun delete(notesEntity: NotesEntity){
        notesDao.delete(notesEntity)
    }
    suspend fun update(notesEntity: NotesEntity){
        notesDao.update(notesEntity)
    }
}