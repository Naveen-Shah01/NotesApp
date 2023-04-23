package com.example.splash.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


//creating a Data Model Class and using as entity
@Entity(tableName = "notes-table")
data class NotesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "title")
    var noteTitle: String = "",
    @ColumnInfo(name = "description")
    var notesDescription: String = "",
    @ColumnInfo(name = "timestamp")
    var notesTimestamp: String = "",
    val color: Int = -1
) : Serializable