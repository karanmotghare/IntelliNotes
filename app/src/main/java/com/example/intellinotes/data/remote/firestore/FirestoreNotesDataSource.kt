package com.example.intellinotes.data.remote.firestore

import com.example.intellinotes.data.remote.model.CloudNote
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreNotesDataSource @Inject constructor(
    private val fireStore: FirebaseFirestore,
    private val auth: FirebaseAuth
) {

    private fun notesRef() =
        fireStore
            .collection("users")
            .document(auth.currentUser!!.uid)
            .collection("notes")

    suspend fun upsert(note: CloudNote) {
        notesRef()
            .document(note.id)
            .set(note)
            .await()
    }

}