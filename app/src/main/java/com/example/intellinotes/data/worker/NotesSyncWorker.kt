package com.example.intellinotes.data.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.intellinotes.domain.usecases.SyncNotesUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class NotesSyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val syncNotesUseCase: SyncNotesUseCase
) : CoroutineWorker(context, params){

    override suspend fun doWork(): Result {
        return try {
            syncNotesUseCase()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

}