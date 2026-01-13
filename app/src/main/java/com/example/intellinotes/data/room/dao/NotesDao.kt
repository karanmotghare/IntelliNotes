package com.example.intellinotes.data.room.dao

import androidx.room.*
import com.example.intellinotes.data.room.entity.NoteEntity
import com.example.intellinotes.domain.model.FolderCount
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    /* -------------------- CREATE / UPDATE -------------------- */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertNote(note: NoteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertNotes(notes: List<NoteEntity>)

    /* -------------------- READ -------------------- */

    // All active (non-deleted) notes
    @Query("""
        SELECT * FROM notes
        WHERE isDeleted = 0
        ORDER BY isPinned DESC, updatedAt DESC
    """)
    fun getAllNotes(): Flow<List<NoteEntity>>

    // Notes by folder
    @Query("""
        SELECT * FROM notes
        WHERE folderId = :folderId AND isDeleted = 0
        ORDER BY isPinned DESC, updatedAt DESC
    """)
    fun getNotesByFolder(folderId: String): Flow<List<NoteEntity>>

    // Single note (details/editor screen)
    @Query("""
        SELECT * FROM notes
        WHERE id = :noteId
        LIMIT 1
    """)
    fun getNoteById(noteId: String): Flow<NoteEntity?>

    @Query("""
    SELECT folderId, COUNT(*) as count
    FROM notes
    WHERE isDeleted = 0 
    GROUP BY folderId
""")
    fun getFolderCounts(): Flow<List<FolderCount>>

    /* -------------------- UnSynced Notes -------------------- */
    @Query("SELECT * FROM notes WHERE isSynced = 0")
    suspend fun getUnsyncedNotes(): List<NoteEntity>

    @Query("UPDATE notes SET isSynced = 1, lastSyncedAt = :time WHERE id = :noteId")
    suspend fun markAsSynced(noteId: String, time: Long = System.currentTimeMillis())


    /* -------------------- DATE GROUPING (Apple Notes style) -------------------- */

    @Query("""
        SELECT * FROM notes
        WHERE isDeleted = 0
          AND updatedAt >= :startTime
        ORDER BY updatedAt DESC
    """)
    fun getNotesUpdatedAfter(startTime: Long): Flow<List<NoteEntity>>

    /* -------------------- PIN / DELETE -------------------- */

    @Query("UPDATE notes SET isPinned = :pinned WHERE id = :noteId")
    suspend fun setPinned(noteId: String, pinned: Boolean)

    // Soft delete
    @Query("""
        UPDATE notes
        SET isDeleted = 1, updatedAt = :deletedAt, isSynced = 0, version = version + 1
        WHERE id = :noteId
    """)
    suspend fun softDelete(noteId: String, deletedAt: Long)

    /* -------------------- RECENTLY DELETED -------------------- */

    @Query("""
        SELECT * FROM notes
        WHERE isDeleted = 1
        ORDER BY updatedAt DESC
    """)
    fun getDeletedNotes(): Flow<List<NoteEntity>>

    @Query("""
    UPDATE notes
    SET isDeleted = 0,
        updatedAt = :restoredAt
    WHERE id = :noteId
""")
    suspend fun restoreNote(noteId: String, restoredAt: Long)

    /* -------------------- CLEANUP (future) -------------------- */

    // Hard delete after grace period (WorkManager later)
    @Query("DELETE FROM notes WHERE isDeleted = 1 AND updatedAt < :expiryTime")
    suspend fun permanentlyDeleteExpiredNotes(expiryTime: Long)
}