package com.example.intellinotes.ui.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable

@Composable
fun FolderList(
    folders : List<FolderUiModel>,
    onFolderClick: (String) -> Unit
) {
    LazyColumn {
        items(folders.size){ index ->
            FolderItem(
                folder = folders[index],
                onClick = { onFolderClick(folders[index].id) }
            )
            HorizontalDivider()
        }
    }
}