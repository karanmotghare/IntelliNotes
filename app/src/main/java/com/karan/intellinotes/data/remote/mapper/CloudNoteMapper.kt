package com.karan.intellinotes.data.remote.mapper

import com.karan.intellinotes.data.remote.model.CloudNote
import com.karan.intellinotes.data.room.entity.NoteEntity
import com.karan.intellinotes.data.room.entity.NoteType

fun NoteEntity.toCloud(): CloudNote {
    return CloudNote(
        id = id,
        title = title,
        content = content,
        folderId = folderId,
        noteType = noteType.name,
        isDeleted = isDeleted,
        createdAt = createdAt,
        updatedAt = updatedAt,
        version = version
    )
}

fun CloudNote.toEntity(): NoteEntity {
    return NoteEntity(
        id = id,
        title = title,
        content = content,
        folderId = folderId,
        noteType = NoteType.valueOf(noteType),
        isDeleted = isDeleted,
        createdAt = createdAt,
        updatedAt = updatedAt,
        version = version,
        isSynced = true,

        lastSyncedAt = System.currentTimeMillis(),
        isPinned = false,
        aiSummary = null,
        aiKeywords = null,
        aiEmbeddingId = null,
        coverImageUri = null,
        attachmentsCount = 0,
    )
}