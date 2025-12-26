package com.example.intellinotes.domain.model

enum class FolderType {
    SYSTEM,
    USER
}

data class Folder (
    val id: String,
    val title: String,
    val noteCount: Int,
    val type: FolderType
)

data class FolderCount(
    val folderId: String,
    val count: Int
)
