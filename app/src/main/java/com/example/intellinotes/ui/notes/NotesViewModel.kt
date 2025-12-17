package com.example.intellinotes.ui.notes

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intellinotes.data.repository.repo.NoteRepository
import com.example.intellinotes.ui.notes.mapper.toUiModel
import com.example.intellinotes.domain.GroupNotesByDateUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val repository: NoteRepository,
    private val groupNotesByDate: GroupNotesByDateUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val folderId: String =
        checkNotNull(savedStateHandle["folderId"])

    val uiState: StateFlow<NotesUiState> =
        repository.getNotesByFolder(folderId)
            .map {notes ->
                if(notes.isEmpty()){
                    NotesUiState.Empty
                }else{
                    val uiModels = notes.map { it.toUiModel() }
                    val sections = groupNotesByDate(uiModels)

                    NotesUiState.Success(sections)
                }

            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = NotesUiState.Loading
            )
}