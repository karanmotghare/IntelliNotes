package com.karan.intellinotes.data.repository.repo

import com.karan.intellinotes.domain.model.Folder
import kotlinx.coroutines.flow.Flow

interface FolderRepository {
    fun getFolders(): Flow<List<Folder>>
    suspend fun createFolder(title: String)
}