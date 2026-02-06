package com.karan.intellinotes.ui.notes.mapper

import com.karan.intellinotes.data.room.entity.NoteEntity
import com.karan.intellinotes.ui.notes.NoteUiModel

fun NoteEntity.toUiModel(): NoteUiModel {
    return NoteUiModel(
        id = id,
        title = title ?: "Untitled",
        preview = content?.take(100) ?: "",
        updatedAt = updatedAt ?: createdAt,
        isPinned = isPinned
    )
}