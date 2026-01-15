package com.example.intellinotes.data.repository.repo

import com.example.intellinotes.data.remote.model.CloudNote

interface NotesRemoteRepository {

    suspend fun uploadNote(note: CloudNote)

    suspend fun fetchAllNotes(): List<CloudNote>

}