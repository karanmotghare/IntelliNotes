package com.example.intellinotes.ui.note

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.intellinotes.R

@Composable
fun NoteScreen(
    onBack: () -> Unit,
    viewModel: NoteViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current

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
                    viewModel.saveNoteIfNeeded()
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

        when(val uiState = state) {
            is NoteUiState.Loading -> {
                Text(text = "Loading...")
            }

            is NoteUiState.Empty -> {
            }

            is NoteUiState.Error -> {
                Text(text = uiState.message ?: "Something went wrong")
            }

            is NoteUiState.Success -> {
                NoteContent(
                    note = uiState.note,
                    onEditEnabled = { viewModel.enableEdit() },
                    onTitleChange =  viewModel::onTitleChange,
                    onContentChange = viewModel::onContentChange
                )
            }
        }

    }

    BackHandler {
        viewModel.saveNoteIfNeeded()
        onBack()
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_STOP) {
                viewModel.saveNoteIfNeeded()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

}