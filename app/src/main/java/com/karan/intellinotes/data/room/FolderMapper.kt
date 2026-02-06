package com.karan.intellinotes.data.room

import com.karan.intellinotes.data.room.entity.FolderEntity
import com.karan.intellinotes.domain.model.Folder

fun FolderEntity.toDomain(noteCount: Int): Folder {
    return Folder(
        id = id,
        title = title,
        noteCount = noteCount,
        isSystem = isSystem
    )
}