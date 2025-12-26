package com.example.intellinotes.data.repository.repo

import com.example.intellinotes.domain.model.Folder
import kotlinx.coroutines.flow.Flow

interface FolderRepository {
    fun getFolders(): Flow<List<Folder>>
    suspend fun createFolder(title: String)
}