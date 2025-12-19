package com.example.intellinotes.ui.notes

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.example.intellinotes.ui.notes.components.NotesGrid

@Composable
fun NotesList(
    sections: List<NotesSection>,
    onNoteClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        sections.forEach { section ->
            item {
                SectionHeader(title = section.title)
            }

            item {
                NotesGrid(
                    notes = section.notes,
                    onNoteClick = onNoteClick
                )
            }
        }
    }
}

@Composable
private fun SectionHeader(title: String) {
    Text(
        text = title,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(vertical = 12.dp)
    )
}