package com.karan.intellinotes.domain.usecases

import com.karan.intellinotes.data.repository.repo.NoteRepository
import javax.inject.Inject

class RestoreNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(noteId: String) {
        repository.restoreNote(noteId)
    }
}
