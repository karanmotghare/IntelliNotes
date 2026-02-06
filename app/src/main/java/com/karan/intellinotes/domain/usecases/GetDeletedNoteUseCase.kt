package com.karan.intellinotes.domain.usecases

import com.karan.intellinotes.data.repository.repo.NoteRepository
import javax.inject.Inject

class GetDeletedNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke() = noteRepository.getDeletedNotes()
}