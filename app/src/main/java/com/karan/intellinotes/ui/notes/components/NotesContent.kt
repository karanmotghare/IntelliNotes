package com.karan.intellinotes.ui.notes.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.karan.intellinotes.ui.notes.NotesList
import com.karan.intellinotes.ui.notes.NotesSection
import com.karan.intellinotes.ui.notes.NotesTopBar

@Composable
fun NotesContent(
    sections: List<NotesSection>,
    onBackClick: () -> Unit,
    onNoteClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 12.dp)
    ) {
        NotesTopBar(onBackClick)

        Spacer(modifier = Modifier.height(16.dp))

        NotesList(
            sections = sections,
            onNoteClick = onNoteClick
        )
    }

}