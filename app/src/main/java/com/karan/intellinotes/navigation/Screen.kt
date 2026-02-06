package com.karan.intellinotes.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Notes : Screen("notes/{folderId}"){
        fun passFolderId(folderId: String) = "notes/$folderId"
    }
    data object NotesDetails : Screen("note_details/{noteId}"){
        fun passNoteId(noteId: String) = "note_details/$noteId"
    }
}