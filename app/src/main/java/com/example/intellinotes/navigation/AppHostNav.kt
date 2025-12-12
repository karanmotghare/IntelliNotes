package com.example.intellinotes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.intellinotes.ui.details.NoteDetailsScreen
import com.example.intellinotes.ui.home.HomeScreen
import com.example.intellinotes.ui.notes.NotesScreen

@Composable
fun AppHostNav(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){

        composable(Screen.Home.route){
            HomeScreen(navController)
        }

        composable(Screen.Notes.route){
            NotesScreen(navController)
        }

        composable(Screen.NotesDetails.route){ navBackStackEntry ->  
            val noteId = navBackStackEntry.arguments?.getString("noteId") ?: return@composable
            NoteDetailsScreen(navController, noteId)
        }

    }
}