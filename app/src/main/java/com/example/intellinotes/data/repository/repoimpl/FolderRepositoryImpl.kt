package com.example.intellinotes.data.repository.repoimpl

import com.example.intellinotes.data.repository.repo.FolderRepository
import com.example.intellinotes.data.room.dao.FolderDao
import com.example.intellinotes.data.room.dao.NotesDao
import com.example.intellinotes.data.room.entity.FolderEntity
import com.example.intellinotes.domain.model.Folder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import java.util.UUID
import javax.inject.Inject

class FolderRepositoryImpl @Inject constructor(
    private val folderDao: FolderDao,
    private val notesDao: NotesDao
) : FolderRepository{

    override fun getFolders(): Flow<List<Folder>> =
        combine(
            folderDao.observeFolders(),
            notesDao.getFolderCounts()
        ) { folders, counts ->

            val countMap = counts.associateBy(
                keySelector = { it.folderId },
                valueTransform = { it.count }
            )

            folders.map { folder ->
                Folder(
                    id = folder.id,
                    title = folder.title,
                    noteCount = countMap[folder.id] ?: 0,
                    isSystem = folder.isSystem
                )
            }
        }

    override suspend fun createFolder(title: String) {
        val now = System.currentTimeMillis()

        folderDao.insertFolder(
            FolderEntity(
                id = UUID.randomUUID().toString(),
                title = title,
                isSystem = false,
                createdAt = now,
                updatedAt = now
            )
        )
    }

}