package com.example.intellinotes.ui.note.mapper

import com.example.intellinotes.data.room.entity.NoteEntity
import com.example.intellinotes.domain.model.NoteModel

fun NoteEntity.toDomain(): NoteModel {
    return NoteModel(
        noteId = id,
        title = title.orEmpty(),
        content = content.orEmpty(),
        updatedAt = updatedAt ?: createdAt
    )
}