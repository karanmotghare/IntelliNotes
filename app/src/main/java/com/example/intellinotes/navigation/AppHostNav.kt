package com.example.intellinotes.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.intellinotes.ui.details.NoteDetailsScreen
import com.example.intellinotes.ui.home.HomeScreen
import com.example.intellinotes.ui.notes.NotesScreen

@Composable
fun AppHostNav(navController: NavHostController) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route
        ) {

            composable(Screen.Home.route) {
                HomeScreen(navController)
            }

            composable(Screen.Notes.route) {
                NotesScreen(navController)
            }

            composable(Screen.NotesDetails.route) { navBackStackEntry ->
                val noteId = navBackStackEntry.arguments?.getString("noteId") ?: return@composable
                NoteDetailsScreen(navController, noteId)
            }

        }
    }
}