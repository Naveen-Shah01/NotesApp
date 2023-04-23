package com.example.splash.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.splash.models.NotesEntity
import com.example.splash.utils.DATABASE_NAME


@Database(entities = [NotesEntity::class],version = 1,exportSchema = false)
abstract class NotesDatabase: RoomDatabase()  {
    abstract fun getNotesDao(): NotesDao

    companion object {
        @Volatile
        private var INSTANCE: NotesDatabase? = null
        fun getInstance(context: Context): NotesDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext, NotesDatabase::class.java, DATABASE_NAME
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}


