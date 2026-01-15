package com.example.intellinotes.domain.usecases

import com.example.intellinotes.data.remote.firestore.FirestoreNotesDataSource
import com.example.intellinotes.data.remote.mapper.toCloud
import com.example.intellinotes.data.remote.mapper.toEntity
import com.example.intellinotes.data.repository.repo.NoteRepository
import com.example.intellinotes.data.repository.repo.NotesRemoteRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class SyncNotesUseCase @Inject constructor(
    private val repository: NoteRepository,
    private val notesRemoteRepository: NotesRemoteRepository,
    private val remote: FirestoreNotesDataSource
) {

    suspend operator fun invoke() {
        pushLocalChanges()
        pullRemoteChanges()
    }

    private suspend fun pushLocalChanges() {
        val unsyncedNotes = repository.getUnsyncedNotes()

        unsyncedNotes.forEach { note ->
            remote.upsert(note.toCloud())
            repository.markAsSynced(note.id, System.currentTimeMillis())
        }
    }

    private suspend fun pullRemoteChanges() {
        val cloudNotes = notesRemoteRepository.fetchAllNotes()

        cloudNotes.forEach { cloudNote ->
            val localNote = repository.getNoteById(cloudNote.id).firstOrNull()

            when {
                localNote == null -> {
                    // New note from another device
                    repository.upsertNote(cloudNote.toEntity())
                }


                cloudNote.version > localNote.version -> {
                    // Cloud is newer → overwrite local
                    repository.upsertNote(cloudNote.toEntity())
                }

                cloudNote.version < localNote.version -> {
                    // Local is newer → push again later
                    // Do nothing (will sync in next cycle)
                }
            }
        }
    }
}