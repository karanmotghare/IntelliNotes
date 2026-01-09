package com.example.intellinotes.ui.note.mapper

import com.example.intellinotes.data.room.entity.NoteEntity
import com.example.intellinotes.data.room.entity.NoteType
import com.example.intellinotes.domain.model.NoteModel

fun NoteEntity.toDomain(): NoteModel {
    return NoteModel(
        noteId = id,
        title = title.orEmpty(),
        content = content.orEmpty(),
        updatedAt = updatedAt ?: createdAt
    )
}

fun NoteModel.toEntity(folderId: String, existing: NoteEntity? = null): NoteEntity{
    val now = System.currentTimeMillis()

    return NoteEntity(
        id = noteId,
        title = title,
        content = content,
        noteType = NoteType.TEXT,
        folderId = folderId,
        createdAt = existing?.createdAt ?: now,
        updatedAt = updatedAt,
        isPinned = existing?.isPinned ?: false,
        isDeleted = false,
        aiSummary = null,
        aiKeywords = null,
        aiEmbeddingId = null,
        coverImageUri = null,
        attachmentsCount = 0,
        isSynced = false,
        lastSyncedAt = null,
        version = (existing?.version ?: 0) + 1,
    )
}