package com.example.intellinotes.domain

import com.example.intellinotes.data.repository.repo.NoteRepository
import javax.inject.Inject

class GetNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) { // not using SUSPEND Because you are observing a note, not fetching it once.
    operator fun invoke(noteId: String) =
        repository.getNoteById(noteId)
}