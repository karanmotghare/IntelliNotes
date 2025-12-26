package com.example.intellinotes.core.utils

import com.example.intellinotes.domain.model.FolderType

fun String.toReadableTitle(): String =
    when (this) {
        "NOTES" -> "Notes"
        "RECENTLY_DELETED" -> "Recently Deleted"
        else -> this.replaceFirstChar { it.uppercase() }
    }

object FolderTypeResolver {
    fun resolve(folderId: String): FolderType =
        when (folderId) {
            "NOTES", "RECENTLY_DELETED" -> FolderType.SYSTEM
            else -> FolderType.USER
        }
}
