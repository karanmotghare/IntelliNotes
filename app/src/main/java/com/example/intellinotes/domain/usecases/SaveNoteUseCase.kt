package com.example.intellinotes.domain.usecases

import com.example.intellinotes.data.repository.repo.NoteRepository
import com.example.intellinotes.data.room.NoteEntity
import javax.inject.Inject

class SaveNoteUseCase @Inject constructor(
    private val repository: NoteRepository
){
    suspend operator fun invoke(note: NoteEntity){
        repository.upsertNote(note)
    }
}