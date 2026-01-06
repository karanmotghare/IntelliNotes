package com.example.intellinotes

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.intellinotes.navigation.AppHostNav
import com.example.intellinotes.navigation.Screen

@Composable
fun IntelliNotesRoot(
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    LaunchedEffect(Unit) {
        mainViewModel.navigateToNote.collect { noteId ->
            navController.navigate(
                Screen.NotesDetails.passNoteId(noteId)
            )
        }
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { mainViewModel.onNewNoteClicked() }
            ) {
                Icon(Icons.Default.Edit, contentDescription = "New note")
            }
        }
    ) { innerPadding ->
        AppHostNav(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}