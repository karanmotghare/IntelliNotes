package com.karan.intellinotes.domain.model

data class NoteModel (
    val noteId: String,
    val title: String,
    val content: String,
    val updatedAt: Long,
)