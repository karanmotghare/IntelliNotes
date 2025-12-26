package com.example.intellinotes.data.room

import com.example.intellinotes.data.room.entity.FolderEntity
import com.example.intellinotes.domain.model.Folder

fun FolderEntity.toDomain(noteCount: Int): Folder {
    return Folder(
        id = id,
        title = title,
        noteCount = noteCount,
        isSystem = isSystem
    )
}