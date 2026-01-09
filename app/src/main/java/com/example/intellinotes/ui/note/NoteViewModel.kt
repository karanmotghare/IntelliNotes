package com.example.intellinotes.ui.note

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intellinotes.domain.model.NoteModel
import com.example.intellinotes.domain.usecases.GetNoteUseCase
import com.example.intellinotes.domain.usecases.SaveNoteUseCase
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
    private val saveNote: SaveNoteUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val noteId: String? =
        savedStateHandle["noteId"]
    private val folderId: String =
        savedStateHandle["folderId"] ?: "NOTES"

    private val _uiState = MutableStateFlow<NoteUiState>(NoteUiState.Loading)
    val uiState: StateFlow<NoteUiState> = _uiState.asStateFlow()

    private var originalNote: NoteUiModel? = null


    init {
        loadNote()
    }

    /* ---------------- LOAD ---------------- */
    private fun loadNote() {
        // FAB → new note
        if (noteId == null) {
            val newNote = NoteUiModel(
                noteId = "",
                title = "",
                content = "",
                updatedAt = System.currentTimeMillis(),
                mode = NoteMode.WRITE
            )

            originalNote = newNote
            _uiState.value = NoteUiState.Success(newNote)
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
                        originalNote = uiModel
                        _uiState.value = NoteUiState.Success(uiModel)
                    }
                }
        }
    }

    /* ---------------- EDITING ---------------- */

    fun enableEdit() {
        updateMode(NoteMode.WRITE)
    }

    fun disableEdit() {
        updateMode(NoteMode.READ)
    }

    private fun updateMode(mode: NoteMode) {
        // this will be converted to MutableStateFlow later when we add editing
        val current = _uiState.value

        if (current is NoteUiState.Success) {
            _uiState.value = current.copy(
                note = current.note.copy(
                    mode = mode
                )
            )
        }
    }

    fun onTitleChange(title: String) {
        val current = _uiState.value
        if (current is NoteUiState.Success) {
            _uiState.value = current.copy(
                note = current.note.copy(title = title)
            )
        }
    }

    fun onContentChange(content: String){
        val current = _uiState.value
        if(current is NoteUiState.Success){
            _uiState.value = current.copy(
                note = current.note.copy(content = content)
            )
        }
    }

    /* ---------------- SAVING ---------------- */

    fun saveNoteIfNeeded(){
        val state = _uiState.value
        if(state !is NoteUiState.Success) return

        val note = state.note

        if(isEmpty(note)) return

        if (!isDirty(note)) return

        viewModelScope.launch {
            saveNote(
                note = NoteModel(
                    noteId = note.noteId,
                    title = note.title,
                    content = note.content,
                    updatedAt = System.currentTimeMillis()
                ),
                folderId = folderId
            )
            originalNote = note.copy()
        }
    }

    //LIFE-CYCLE trigger
    override fun onCleared() {
        saveNoteIfNeeded()
        super.onCleared()
    }

    private fun isDirty(current: NoteUiModel): Boolean {
        val original = originalNote ?: return true

        return original.title != current.title ||
                original.content != current.content
    }

    private fun isEmpty(note: NoteUiModel): Boolean {
        return note.title.isBlank() && note.content.isBlank()
    }

}