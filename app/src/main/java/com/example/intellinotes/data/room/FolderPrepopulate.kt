package com.example.intellinotes.data.room

import com.example.intellinotes.data.room.dao.FolderDao
import com.example.intellinotes.data.room.entity.FolderEntity
import javax.inject.Inject

class FolderPrepopulate @Inject constructor(
    private val dao: FolderDao
){
    suspend fun seedIfNeeded() {
        if(dao.folderCount() > 0) return // no need to repopulate once done!

        val now = System.currentTimeMillis()

        dao.insertFolder(
            FolderEntity(
                id = "NOTES",
                title = "Notes",
                isSystem = true,
                createdAt = now,
                updatedAt = now
            )
        )

        dao.insertFolder(
            FolderEntity(
                id = "RECENTLY_DELETED",
                title = "Recently Deleted",
                isSystem = true,
                createdAt = now,
                updatedAt = now
            )
        )
    }
}