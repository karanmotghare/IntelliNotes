package com.karan.intellinotes.ui.note


sealed interface NoteUiState {

    data object Loading : NoteUiState

    data object Empty : NoteUiState

    data class Success(
        val note: NoteUiModel
    ) : NoteUiState

    data class Error(
        val message: String
    ) : NoteUiState
}