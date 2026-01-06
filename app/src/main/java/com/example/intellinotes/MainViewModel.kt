package com.example.intellinotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intellinotes.domain.usecases.CreateNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val createNoteUseCase: CreateNoteUseCase
) : ViewModel(){
    /* -------------------- CURRENT CONTEXT -------------------- */

    // Current folder where FAB should create note
    private val _currentFolderId = MutableStateFlow<String>("NOTES")
    val currentFolderId = _currentFolderId.asStateFlow()

    fun setCurrentFolder(folderId: String) {
        _currentFolderId.value = folderId
    }

    /* -------------------- NAVIGATION EVENTS -------------------- */

    private val _navigateToNote = MutableSharedFlow<String>()
    val navigateToNote = _navigateToNote.asSharedFlow()

    /* -------------------- FAB ACTION -------------------- */

    fun onNewNoteClicked() {
        viewModelScope.launch {
            val noteId = createNoteUseCase(
                folderId = _currentFolderId.value
            )
            _navigateToNote.emit(noteId)
        }
    }
}