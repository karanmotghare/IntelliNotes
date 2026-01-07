package com.example.intellinotes.ui.note

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intellinotes.domain.usecases.GetNoteUseCase
import com.example.intellinotes.ui.note.mapper.toDomain
import com.example.intellinotes.ui.note.mapper.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val getNote: GetNoteUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val noteId: String? =
        savedStateHandle["noteId"]

    private val _uiState = MutableStateFlow<NoteUiState>(NoteUiState.Loading)
    val uiState: StateFlow<NoteUiState> = _uiState.asStateFlow()

    init {
        loadNote()
    }

    private fun loadNote() {
        // FAB → new note
        if (noteId == null) {
            _uiState.value = NoteUiState.Success(
                NoteUiModel(
                    noteId = "",
                    title = "",
                    content = "",
                    updatedAt = System.currentTimeMillis(),
                    mode = NoteMode.WRITE
                )
            )
            return
        }
        viewModelScope.launch {
            getNote(noteId)
                .catch {
                    _uiState.value = NoteUiState.Error("Failed to load note")
                }
                .collectLatest { note ->
                    if (note == null) {
                        _uiState.value = NoteUiState.Empty
                    } else {
                        val uiModel = note
                            .toDomain()
                            .toUiModel()
                        _uiState.value = NoteUiState.Success(uiModel)
                    }
                }
        }
    }


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