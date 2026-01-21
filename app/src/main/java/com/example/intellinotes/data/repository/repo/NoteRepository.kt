package com.example.intellinotes.data.repository.repo

import com.example.intellinotes.data.room.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotesByFolder(folderId: String): Flow<List<NoteEntity>>

    fun getNoteById(noteId: String): Flow<NoteEntity?>

    suspend fun getDeletedNotes(): List<NoteEntity>

    suspend fun upsertNote(note: NoteEntity)

    suspend fun restoreNote(noteId: String)

    suspend fun softDeleteNote(noteId: String, deletedAt: Long)

    suspend fun permanentlyDeleteExpiredNotes(days: Long)

    suspend fun getUnsyncedNotes(): List<NoteEntity>

    suspend fun markAsSynced(noteId: String, syncedAt: Long)
}