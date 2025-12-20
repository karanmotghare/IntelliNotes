package com.example.intellinotes.data.room
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "notes",
    indices = [
        Index(value = ["folderId"]),
        Index(value = ["updatedAt"]),
        Index(value = ["isDeleted"]),
        Index(value = ["isPinned"])
    ]
)

data class NoteEntity(
    @PrimaryKey
    val id: String,

    val title: String?,
    val content: String?,

    val noteType: NoteType, // TEXT, IMAGE, MIXED, CHECKLIST

    val folderId: String, // NOTES, RECENTLY_DELETED, CUSTOM_FOLDER_ID

    val createdAt: Long,
    val updatedAt: Long?,

    val isPinned: Boolean = false,
    val isDeleted: Boolean = false,

    // --- AI FEATURES ---
    val aiSummary: String?,          // AI-generated short summary
    val aiKeywords: String?,         // comma-separated or JSON
    val aiEmbeddingId: String?,// vector reference (future search)

    // --- MEDIA ---
    val coverImageUri: String?,      // preview image for grid cards
    val attachmentsCount: Int = 0,

    // --- SYNC ---
    val isSynced: Boolean = false,
    val lastSyncedAt: Long?,

    // --- VERSIONING ---
    val version: Int = 1

)

enum class NoteType {
    TEXT,
    IMAGE,
    MIXED,
    CHECKLIST
}