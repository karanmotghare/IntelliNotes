package com.example.intellinotes.data.repository.repoimpl

import com.example.intellinotes.data.repository.repo.NoteRepository
import com.example.intellinotes.data.room.entity.NoteEntity
import com.example.intellinotes.data.room.dao.NotesDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NotesDao
) : NoteRepository {

    override fun getNotesByFolder(folderId: String): Flow<List<NoteEntity>> {
        return noteDao.getNotesByFolder(folderId)
    }

    override fun getNoteById(noteId: String): Flow<NoteEntity?> {
        return noteDao.getNoteById(noteId)
    }

    override suspend fun upsertNote(note: NoteEntity) {
        noteDao.upsertNote(note)
    }

    override suspend fun softDeleteNote(noteId: String) {
        noteDao.softDelete(noteId, System.currentTimeMillis())
    }
}