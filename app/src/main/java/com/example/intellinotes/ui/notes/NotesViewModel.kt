package com.example.intellinotes.ui.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intellinotes.data.repository.repo.NoteRepository
import com.example.intellinotes.data.room.NoteEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class NotesViewModel(
    private val repository: NoteRepository,
    private val folderId: String
) : ViewModel() {

    val notes: StateFlow<List<NoteEntity>> =
        repository.getNotesByFolder(folderId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )
}