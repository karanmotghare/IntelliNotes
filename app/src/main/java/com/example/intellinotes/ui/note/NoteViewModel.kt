package com.example.intellinotes.ui.note

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intellinotes.domain.usecases.GetNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val getNote: GetNoteUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val noteId: String =
        checkNotNull(savedStateHandle["noteId"])

    val uiState: StateFlow<NoteUiState> =
        getNote(noteId)
            .map { note ->
                NoteUiState(
                    noteId = note?.id.orEmpty(),
                    title = note?.title.orEmpty(),
                    content = note?.content.orEmpty(),
                    updatedAt = note?.updatedAt ?: 0L,
                    mode = NoteMode.READ,
                    isLoading = false,
                    error = null
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = NoteUiState(
                    noteId = noteId,
                    isLoading = true
                )
            )

    fun enableEdit() {
        updateMode(NoteMode.WRITE)
    }

    fun disableEdit() {
        updateMode(NoteMode.READ)
    }

    private fun updateMode(mode: NoteMode) {
        // this will be converted to MutableStateFlow later when we add editing
    }

}