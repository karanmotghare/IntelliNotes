package com.example.intellinotes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
            Row (
                modifier = Modifier.padding(end = 16.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ){
                FloatingActionButton(
                    onClick = { mainViewModel.syncNow() }
                ) {
                    Icon(Icons.Default.Check, contentDescription = "sync note")
                }
                FloatingActionButton(
                    onClick = { mainViewModel.onNewNoteClicked() }
                ) {
                    Icon(Icons.Default.Edit, contentDescription = "New note")
                }
            }
        }
    ) { innerPadding ->
        AppHostNav(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}