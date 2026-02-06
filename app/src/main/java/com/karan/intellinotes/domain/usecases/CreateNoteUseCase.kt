package com.karan.intellinotes.domain.usecases

import com.karan.intellinotes.data.repository.repo.NoteRepository
import com.karan.intellinotes.data.room.entity.NoteEntity
import com.karan.intellinotes.data.room.entity.NoteType
import java.util.UUID
import javax.inject.Inject

class CreateNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(folderId: String): String {
        val noteId = UUID.randomUUID().toString()
        val now = System.currentTimeMillis()

        val note = NoteEntity(
            id = noteId,
            title = "",
            content = "",
            noteType = NoteType.TEXT,
            folderId = folderId,
            createdAt = now,
            updatedAt = now,
            isPinned = false,
            isDeleted = false,
            aiSummary = null,
            aiKeywords = null,
            aiEmbeddingId = null,
            coverImageUri = null,
            attachmentsCount = 0,
            isSynced = false,
            lastSyncedAt = null,
            version = 1
        )
        repository.upsertNote(note)
        return noteId
    }
}