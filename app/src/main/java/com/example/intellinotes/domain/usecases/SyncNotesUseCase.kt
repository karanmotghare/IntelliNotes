package com.example.intellinotes.domain.usecases

import com.example.intellinotes.data.remote.mapper.toCloud
import com.example.intellinotes.data.remote.mapper.toEntity
import com.example.intellinotes.data.repository.repo.NoteRepository
import com.example.intellinotes.data.repository.repo.NotesRemoteRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class SyncNotesUseCase @Inject constructor(
    private val repository: NoteRepository,
    private val notesRemoteRepository: NotesRemoteRepository,
) {

    suspend operator fun invoke() {
        pushLocalChanges()
        pullRemoteChanges()
    }

    private suspend fun pushLocalChanges() {
        val unsyncedNotes = repository.getUnsyncedNotes()
        val deletedNotes = repository.getDeletedNotes()

        unsyncedNotes.forEach { note ->
            try {
                notesRemoteRepository.uploadNote(note.toCloud())
                repository.markAsSynced(note.id, note.updatedAt ?: System.currentTimeMillis())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }

//        “We never hard-delete during sync.
//        Deletions are versioned state changes.
//        Physical cleanup is handled asynchronously via background jobs to ensure consistency across offline devices.”
        deletedNotes
            .filter { it.isSynced }
            .forEach { note ->
                try {
                    notesRemoteRepository.deleteNote(note.toCloud())
                    repository.markAsSynced(note.id, note.updatedAt ?: System.currentTimeMillis())
                }catch (e: Exception){
                    e.printStackTrace()
                }
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
                    if(cloudNote.isDeleted){
                        repository.softDeleteNote(cloudNote.id, cloudNote.updatedAt ?: System.currentTimeMillis())
                    }else {
                        repository.upsertNote(cloudNote.toEntity())
                    }
                }

                cloudNote.version < localNote.version -> {
                    // Local is newer → push again later
                    // Do nothing (will sync in next cycle)
                }
            }
        }
    }
}