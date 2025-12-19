package com.example.intellinotes.ui.notes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.intellinotes.ui.notes.components.NotesContent
import com.example.intellinotes.ui.notes.components.NotesEmpty
import com.example.intellinotes.ui.notes.components.NotesError
import com.example.intellinotes.ui.notes.components.NotesLoading


@Composable
fun NotesScreen(
    onBackClick: () -> Unit,
    onNoteClick: (String) -> Unit,
    viewModel: NotesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        NotesUiState.Loading -> {
            NotesLoading()
        }

        NotesUiState.Empty -> {
            NotesEmpty()
        }

        is NotesUiState.Error -> {
            NotesError(
                message = (uiState as NotesUiState.Error).message
            )
        }

        is NotesUiState.Success -> {
            NotesContent(
                sections = (uiState as NotesUiState.Success).sections,
                onBackClick = onBackClick,
                onNoteClick = onNoteClick
            )
        }
    }
}