package com.example.intellinotes.ui.note

data class NoteUiState(
    val noteId: String,
    val title: String = "",
    val content: String = "",
    val updatedAt: Long = 0L,
    val mode: NoteMode = NoteMode.READ,
    val isLoading: Boolean = false,
    val error: String? = null
)