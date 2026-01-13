package com.example.intellinotes.domain.usecases

import com.example.intellinotes.data.remote.firestore.FirestoreNotesDataSource
import com.example.intellinotes.data.remote.mapper.toCloud
import com.example.intellinotes.data.repository.repo.NoteRepository
import javax.inject.Inject

class SyncNotesUseCase @Inject constructor(
    private val repository: NoteRepository,
    private val remote: FirestoreNotesDataSource
) {

    suspend operator fun invoke() {
        val unsyncedNotes = repository.getUnsyncedNotes()

        unsyncedNotes.forEach { note ->
            remote.upsert(note.toCloud())
            repository.markAsSynced(note.id)
        }
    }
}