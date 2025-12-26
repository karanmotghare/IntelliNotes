package com.example.intellinotes.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "folders")
data class FolderEntity (
    @PrimaryKey
    val id: String,// "NOTES", "RECENTLY_DELETED", UUID for user folders

    val title: String,

    val isSystem: Boolean, // true → cannot delete/rename

    val createdAt: Long,

    val updatedAt: Long
)