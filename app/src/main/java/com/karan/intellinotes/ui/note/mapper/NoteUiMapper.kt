package com.karan.intellinotes.ui.note.mapper

import com.karan.intellinotes.domain.model.NoteModel
import com.karan.intellinotes.ui.note.NoteMode
import com.karan.intellinotes.ui.note.NoteUiModel

fun NoteModel.toUiModel(): NoteUiModel{
    return NoteUiModel(
        noteId = noteId,
        title = title,
        content = content,
        updatedAt = updatedAt,
        mode = NoteMode.READ
    )
}