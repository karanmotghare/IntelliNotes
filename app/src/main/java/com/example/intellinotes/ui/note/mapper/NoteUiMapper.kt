package com.example.intellinotes.ui.note.mapper

import com.example.intellinotes.domain.model.NoteModel
import com.example.intellinotes.ui.note.NoteMode
import com.example.intellinotes.ui.note.NoteUiModel

fun NoteModel.toUiModel(): NoteUiModel{
    return NoteUiModel(
        noteId = noteId,
        title = title,
        content = content,
        updatedAt = updatedAt,
        mode = NoteMode.READ
    )
}