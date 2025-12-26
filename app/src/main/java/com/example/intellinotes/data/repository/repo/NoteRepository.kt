package com.example.intellinotes.data.repository.repo

import com.example.intellinotes.data.room.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotesByFolder(folderId: String): Flow<List<NoteEntity>>

    fun getNoteById(noteId: String): Flow<NoteEntity?>

    suspend fun upsertNote(note: NoteEntity)

    suspend fun softDeleteNote(noteId: String)
}