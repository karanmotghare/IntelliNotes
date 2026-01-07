package com.example.intellinotes.ui.note

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NoteContent(
    note: NoteUiModel,
    onEditEnabled: () -> Unit
) {
    var title by remember(note.title) { mutableStateOf(note.title) }
    var content by remember(note.content) { mutableStateOf(note.content) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                enabled = note.mode == NoteMode.READ
            ) {
                onEditEnabled()
            }
    ) {
        if (note.mode == NoteMode.READ) {

            Text(
                text = title.ifBlank { "Untitled" },
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = content.ifBlank { "Start writing..." },
                fontSize = 16.sp
            )

        } else{
            BasicTextField(
                value = title,
                onValueChange = { title = it },
                textStyle = androidx.compose.ui.text.TextStyle(fontSize = 24.sp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            BasicTextField(
                value = content,
                onValueChange = { content = it },
                textStyle = androidx.compose.ui.text.TextStyle(fontSize = 16.sp),
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}