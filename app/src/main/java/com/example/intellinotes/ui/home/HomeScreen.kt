package com.example.intellinotes.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    onOpenNotes: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 16.dp)
    ) {

        TopBar()

        Spacer(modifier = Modifier.height(16.dp))

        when (uiState) {
            is HomeUiState.Error -> Text("Something went wrong")
            HomeUiState.Loading -> CircularProgressIndicator()
            is HomeUiState.Success -> {
                FolderList(
                    folders = (uiState as HomeUiState.Success).folders,
                    onFolderClick = onOpenNotes
                )
            }
        }
    }
}