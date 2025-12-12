package com.example.intellinotes.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Notes : Screen("notes")
    data object NotesDetails : Screen("note_details/{noteId}"){
        fun passNoteId(noteId: String) = "note_details/$noteId"
    }
}