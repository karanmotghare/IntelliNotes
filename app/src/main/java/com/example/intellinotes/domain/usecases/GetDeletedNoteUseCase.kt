package com.example.intellinotes.domain.usecases

import com.example.intellinotes.data.repository.repo.NoteRepository
import javax.inject.Inject

class GetDeletedNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    operator fun invoke() = noteRepository.getDeletedNotes()
}