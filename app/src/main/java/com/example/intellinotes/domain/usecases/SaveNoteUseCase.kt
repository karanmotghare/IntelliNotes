package com.example.intellinotes.domain.usecases

import com.example.intellinotes.data.repository.repo.NoteRepository
import com.example.intellinotes.data.room.entity.NoteEntity
import com.example.intellinotes.domain.model.NoteModel
import com.example.intellinotes.ui.note.mapper.toEntity
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class SaveNoteUseCase @Inject constructor(
    private val repository: NoteRepository
){
    suspend operator fun invoke(note: NoteModel, folderId: String){
        val existing = repository.getNoteById(note.noteId).firstOrNull()
        repository.upsertNote(
            note.toEntity(
            folderId = folderId,
            existing = existing
            )
        )
    }
}