package com.example.intellinotes.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        NoteEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    NoteTypeConverter::class
)
abstract class IntelliNotesDatabase: RoomDatabase() {
    abstract fun notesDao(): NotesDao
}