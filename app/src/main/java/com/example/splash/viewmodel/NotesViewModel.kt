package com.example.splash.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.splash.database.NotesDatabase
import com.example.splash.models.NotesEntity
import com.example.splash.notesRepository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    val allNotes: LiveData<List<NotesEntity>>
    val notesRepository: NotesRepository

    init {
        val dao = NotesDatabase.getInstance(application).getNotesDao()
        notesRepository = NotesRepository(dao)
        allNotes = notesRepository.allNotes
    }

    fun deleteNote(notesEntity: NotesEntity) = viewModelScope.launch(Dispatchers.IO) {
        notesRepository.delete(notesEntity)
    }

    fun updateNote(notesEntity: NotesEntity) = viewModelScope.launch(Dispatchers.IO) {
        notesRepository.update(notesEntity)
    }

    fun addNote(notesEntity: NotesEntity) = viewModelScope.launch(Dispatchers.IO) {
        notesRepository.insert(notesEntity)
    }

}