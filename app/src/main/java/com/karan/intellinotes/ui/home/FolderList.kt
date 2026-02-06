package com.karan.intellinotes.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.karan.intellinotes.R

@Composable
fun FolderList(
    folders : List<FolderUiModel>,
    onFolderClick: (String) -> Unit
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        tonalElevation = 2.dp,
        color = colorResource(id = R.color.white),
    ) {
        LazyColumn {
            items(folders.size) { index ->
                FolderItem(
                    folder = folders[index],
                    onClick = { onFolderClick(folders[index].id) }
                )
                if(index < folders.lastIndex) {
                    HorizontalDivider(modifier = Modifier.padding(start = 48.dp, end = 12.dp), color = colorResource(R.color.yellow_tint))
                }
            }
        }
    }
}