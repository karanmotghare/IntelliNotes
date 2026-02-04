package com.example.intellinotes.domain.usecase

import com.example.intellinotes.data.remote.mapper.toCloud
import com.example.intellinotes.data.repository.repo.NoteRepository
import com.example.intellinotes.data.repository.repo.NotesRemoteRepository
import com.example.intellinotes.data.room.entity.NoteEntity
import com.example.intellinotes.data.room.entity.NoteType
import com.example.intellinotes.domain.usecases.SyncNotesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class SyncNotesUseCaseTest {

    // ─────────────────────
    // Step 1: Create mocks
    // ─────────────────────
    private val repository : NoteRepository = mock()
    private val remoteRepository : NotesRemoteRepository = mock()

    private lateinit var useCase: SyncNotesUseCase

    @Before
    fun setup() {
        useCase = SyncNotesUseCase(
            repository,
            remoteRepository
        )
    }

    // ─────────────────────
    // Step 2: Fake data
    // ─────────────────────

    private val note = NoteEntity(
        id = "1",
        title = "Test",
        content = "Content",
        noteType = NoteType.TEXT,
        isDeleted = false,
        createdAt = 1L,
        updatedAt = 2L,
        version = 1,
        isSynced = false,
        lastSyncedAt = null,
        isPinned = false,
        aiSummary = null,
        aiKeywords = null,
        aiEmbeddingId = null,
        coverImageUri = null,
        attachmentsCount = 0,
        folderId = "1234"
    )

    // ─────────────────────
    // Step 3: Actual tests
    // ─────────────────────
    @Test
    fun `uploads unsynced notes and marked them synced`() = runTest {
        //given
        whenever(repository.getUnsyncedNotes()).thenReturn(listOf(note))
        whenever(repository.getDeletedNotes()).thenReturn(emptyList())
        whenever(remoteRepository.fetchAllNotes()).thenReturn(emptyList())

        //when
        useCase()

        //then
        val expectedCloudNote = note.toCloud()
        verify(remoteRepository).uploadNote(expectedCloudNote)
        verify(repository).markAsSynced(eq(note.id), any())
    }

    @Test
    fun `deleted notes and marked them synced`() = runTest {

        val deletedNote = note.copy(isDeleted = true, isSynced = false)
        whenever(repository.getUnsyncedNotes()).thenReturn(emptyList())
        whenever(repository.getDeletedNotes()).thenReturn(listOf(deletedNote))
        whenever(remoteRepository.fetchAllNotes()).thenReturn(emptyList())

        useCase()

        val expectedCloudNote = deletedNote.toCloud()
        verify(remoteRepository).deleteNote(expectedCloudNote)
        verify(repository).markAsSynced(eq(deletedNote.id), any())
    }

    @Test
    fun `pull remote notes`() = runTest {
        val localNote = note.copy(version = 1)
        val remoteNote = note.copy(version = 2).toCloud()

        whenever(repository.getUnsyncedNotes()).thenReturn(emptyList())
        whenever(repository.getDeletedNotes()).thenReturn(emptyList())
        whenever(remoteRepository.fetchAllNotes()).thenReturn(listOf(remoteNote))
        whenever(repository.getNoteById(localNote.id)).thenReturn(flowOf(localNote))

        useCase()

        verify(repository).upsertNote(any())
    }


}