package com.karan.intellinotes.data.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.karan.intellinotes.data.repository.repo.NoteRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject


@HiltWorker
class NotesCleanupWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val repository: NoteRepository
) : CoroutineWorker(context, params){

    override suspend fun doWork(): Result {
        val thirtyDaysAgo =
            System.currentTimeMillis() - 30L * 24 * 60 * 60 * 1000

        repository.permanentlyDeleteExpiredNotes(thirtyDaysAgo)

        return Result.success()
    }

}