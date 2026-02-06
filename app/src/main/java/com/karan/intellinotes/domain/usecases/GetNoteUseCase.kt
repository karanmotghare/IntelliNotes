package com.karan.intellinotes.domain.usecases

import com.karan.intellinotes.data.repository.repo.NoteRepository
import javax.inject.Inject

class GetNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) { // not using SUSPEND Because you are observing a note, not fetching it once.
    operator fun invoke(noteId: String) =
        repository.getNoteById(noteId)
}