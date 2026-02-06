package com.karan.intellinotes.ui.notes

sealed interface NotesUiState {

    data object Loading : NotesUiState

    data object Empty : NotesUiState

    data class Success(
        val sections: List<NotesSection>
    ) : NotesUiState

    data class Error(
        val message: String
    ) : NotesUiState

}