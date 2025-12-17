package com.example.intellinotes.ui.notes.mapper

import com.example.intellinotes.data.room.NoteEntity
import com.example.intellinotes.ui.notes.NoteUiModel

fun NoteEntity.toUiModel(): NoteUiModel {
    return NoteUiModel(
        id = id,
        title = title ?: "Untitled",
        preview = content.take(100),
        updatedAt = updatedAt,
        isPinned = isPinned
    )
}