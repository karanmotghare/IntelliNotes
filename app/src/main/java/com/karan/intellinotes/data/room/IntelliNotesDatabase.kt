package com.karan.intellinotes.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.karan.intellinotes.data.room.dao.FolderDao
import com.karan.intellinotes.data.room.dao.NotesDao
import com.karan.intellinotes.data.room.entity.FolderEntity
import com.karan.intellinotes.data.room.entity.NoteEntity

@Database(
    entities = [
        NoteEntity::class,
        FolderEntity::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(
    NoteTypeConverter::class
)
abstract class IntelliNotesDatabase: RoomDatabase() {
    abstract fun notesDao(): NotesDao
    abstract fun folderDao(): FolderDao
}