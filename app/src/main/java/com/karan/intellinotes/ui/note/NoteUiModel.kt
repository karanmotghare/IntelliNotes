package com.karan.intellinotes.ui.note

data class NoteUiModel (
    val noteId: String,
    val title: String,
    val content: String,
    val updatedAt: Long,
    val mode: NoteMode,
)