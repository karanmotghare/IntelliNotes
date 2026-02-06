package com.karan.intellinotes.ui.home

sealed interface HomeUiState {

    data object  Loading : HomeUiState

    data class Success(
        val folders: List<FolderUiModel>
    ) : HomeUiState

    data class Error(
        val message: String
    ) : HomeUiState
}