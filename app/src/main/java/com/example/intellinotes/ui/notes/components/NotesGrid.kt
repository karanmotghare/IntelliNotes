package com.example.intellinotes.ui.notes.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.intellinotes.ui.notes.NoteUiModel

@Composable
fun NotesGrid(
    notes: List<NoteUiModel>,
    onNoteClick: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3) ,
        modifier = Modifier
                .fillMaxWidth().heightIn(min = 0.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(16.dp),
        userScrollEnabled = false // IMPORTANT, since it is already getting inflated in lazycolumn, and nested scrolling would caouse jank JANK
    ) {
        items(notes) { note ->
            NotesItem(
                note = note,
                onClick = { onNoteClick(note.id) }
            )
        }
    }
}