package com.karan.intellinotes.ui.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.karan.intellinotes.ui.notes.components.NotesItem
import androidx.compose.foundation.lazy.items

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

            items(section.notes.chunked(3)) { rowNotes ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    rowNotes.forEach { note ->
                        Box(modifier = Modifier.weight(1f)) {
                            NotesItem(
                                note = note,
                                onClick = { onNoteClick(note.id) }
                            )
                        }
                    }

                    //since we are getting at max 3 notes at a time in rowNotes so,
                    //if it has less than 3 notes then we must add empty cells
                    // Fill empty cells if row < 3
                    repeat(3 - rowNotes.size) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
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