package com.example.intellinotes.ui.notes

data class NoteUiModel (
    val id: String,
    val title: String,
    val preview: String,
    val updatedAt: Long,
    val isPinned: Boolean
)

data class NotesSection(
    val title: String, // Today, Yesterday, Last 7 Days
    val notes: List<NoteUiModel>
)