package com.example.intellinotes.ui.home.mapper

import com.example.intellinotes.R
import com.example.intellinotes.domain.model.Folder
import com.example.intellinotes.domain.model.FolderType
import com.example.intellinotes.ui.home.FolderUiModel

fun Folder.toUiModel(): FolderUiModel {
    return FolderUiModel(
        id = id,
        title = title,
        count = noteCount,
        iconRes = when (id) {
            "RECENTLY_DELETED" -> R.drawable.yellow_bin
            else -> R.drawable.yellow_folder
        }
    )
}