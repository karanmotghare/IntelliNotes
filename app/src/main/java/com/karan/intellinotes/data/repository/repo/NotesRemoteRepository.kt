package com.karan.intellinotes.data.repository.repo

import com.karan.intellinotes.data.remote.model.CloudNote
import javax.inject.Singleton

@Singleton
interface NotesRemoteRepository {

    suspend fun uploadNote(note: CloudNote)

    suspend fun fetchAllNotes(): List<CloudNote>

    suspend fun deleteNote(note: CloudNote)

}