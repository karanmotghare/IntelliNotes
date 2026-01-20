package com.example.intellinotes.core.dependency_injection

import com.example.intellinotes.data.repository.repo.FolderRepository
import com.example.intellinotes.data.repository.repo.NoteRepository
import com.example.intellinotes.data.repository.repo.NotesRemoteRepository
import com.example.intellinotes.data.repository.repoimpl.FolderRepositoryImpl
import com.example.intellinotes.data.repository.repoimpl.NoteRepositoryImpl
import com.example.intellinotes.data.repository.repoimpl.NotesRemoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindNoteRepository(
        impl: NoteRepositoryImpl
    ): NoteRepository

    @Binds
    abstract fun bindFolderRepository(
        impl: FolderRepositoryImpl
    ): FolderRepository

    @Binds
    @Singleton
    abstract fun bindNotesRemoteRepository(
        impl: NotesRemoteRepositoryImpl
    ): NotesRemoteRepository
}