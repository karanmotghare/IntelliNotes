package com.example.intellinotes.core.dependency_injection

import android.content.Context
import androidx.room.Room
import com.example.intellinotes.data.room.IntelliNotesDatabase
import com.example.intellinotes.data.room.dao.FolderDao
import com.example.intellinotes.data.room.dao.NotesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): IntelliNotesDatabase {
        return Room.databaseBuilder(
            context,
            IntelliNotesDatabase::class.java,
            "intelli_notes_db"
        ).build()
    }

    @Provides
    fun provideFolderDao(db: IntelliNotesDatabase): FolderDao {
        return db.folderDao()
    }

    @Provides
    fun provideNotesDao(db: IntelliNotesDatabase): NotesDao {
        return db.notesDao()
    }
}
