package com.example.intellinotes.data.repository.repoimpl

import com.example.intellinotes.data.remote.model.CloudNote
import com.example.intellinotes.data.repository.repo.NotesRemoteRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class NotesRemoteRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : NotesRemoteRepository {

    private fun notesRef() =
        firestore
            .collection("users")
            .document(auth.currentUser!!.uid)
            .collection("notes")

    override suspend fun uploadNote(note: CloudNote) {
        notesRef()
            .document(note.id)
            .set(note)
            .await()
    }

    override suspend fun fetchAllNotes(): List<CloudNote> {
        return notesRef()
            .get()
            .await()
            .toObjects(CloudNote::class.java)
    }

    //✔ Uses await() → coroutine friendly
    //✔ User-scoped data
    //✔ No Firebase leaks outside data layer

}