package com.example.intellinotes.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.intellinotes.ui.home.HomeScreen
import com.example.intellinotes.ui.note.NoteScreen
import com.example.intellinotes.ui.notes.NotesScreen

@Composable
fun AppHostNav(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route
        ) {

            composable(Screen.Home.route) {
                HomeScreen(
                    onOpenNotes = { folderId ->
                        navController.navigate(Screen.Notes.passFolderId(folderId))
                    }
                )
            }

            composable(Screen.Notes.route) { backStackEntry ->
                val folderId =
                    backStackEntry.arguments?.getString("folderId") ?: "NOTES"

                NotesScreen(
                    folderId = folderId,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onNoteClick = { noteId ->
                        navController.navigate(
                            Screen.NotesDetails.passNoteId(noteId)
                        )
                    }
                )
            }

            composable(Screen.NotesDetails.route) {
                NoteScreen(
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }

        }
    }
}