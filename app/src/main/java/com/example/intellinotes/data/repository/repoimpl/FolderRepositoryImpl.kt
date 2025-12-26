package com.example.intellinotes.data.repository.repoimpl

import com.example.intellinotes.core.utils.FolderTypeResolver
import com.example.intellinotes.core.utils.toReadableTitle
import com.example.intellinotes.data.repository.repo.FolderRepository
import com.example.intellinotes.data.room.NotesDao
import com.example.intellinotes.domain.model.Folder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FolderRepositoryImpl @Inject constructor(
    private val notesDao: NotesDao
) : FolderRepository{

    override fun getFolders(): Flow<List<Folder>> {
        return notesDao.getFolderCounts()
            .map { counts ->
                counts.map { item ->
                    Folder(
                        id = item.folderId,
                        title = item.folderId.toReadableTitle(),
                        noteCount = item.count,
                        type = FolderTypeResolver.resolve(item.folderId)
                    )
                } }
    }

}