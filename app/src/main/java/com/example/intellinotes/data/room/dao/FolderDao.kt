package com.example.intellinotes.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.intellinotes.data.room.entity.FolderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FolderDao {
    @Query("SELECT * FROM folders ORDER BY createdAt ASC")
    fun observeFolders(): Flow<List<FolderEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFolders(folders: List<FolderEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFolder(folder: FolderEntity)

    @Query("UPDATE folders SET title = :title, updatedAt = :updatedAt WHERE id = :folderId")
    suspend fun renameFolder(
        folderId: String,
        title: String,
        updatedAt: Long
    )

    @Query("DELETE FROM folders WHERE id = :folderId")
    suspend fun delete(folderId: String)
}