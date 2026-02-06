package com.karan.intellinotes.ui.home.mapper

import com.karan.intellinotes.R
import com.karan.intellinotes.domain.model.Folder
import com.karan.intellinotes.ui.home.FolderUiModel

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