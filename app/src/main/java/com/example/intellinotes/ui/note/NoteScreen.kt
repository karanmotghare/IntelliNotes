package com.example.intellinotes.ui.note

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.intellinotes.R

@Composable
fun NoteScreen(
    onBack: () -> Unit,
    viewModel: NoteViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    viewModel.disableEdit()
                    onBack()
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_left_chevron),
                    contentDescription = "Back"
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        when {
            state.isLoading -> {
                Text(text = "Loading...")
            }

            state.error != null -> {
                Text(text = state.error ?: "Something went wrong")
            }

            else -> {
                NoteContent(
                    state = state,
                    onEditEnabled = { viewModel.enableEdit() }
                )
            }
        }

    }

}