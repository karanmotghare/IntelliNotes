package com.example.intellinotes.data.worker

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

object NotesCleanupScheduler {

    private const val WORK_NAME = "notes_cleanup_work"

    fun schedule(context: Context) {
        val request =
            PeriodicWorkRequestBuilder<NotesCleanupWorker>(
                1, TimeUnit.DAYS
            )
                .setConstraints(
                    Constraints.Builder()
                        .setRequiresBatteryNotLow(true)
                        .build()
                )
                .build()

        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(
                WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                request
            )
    }

    //For testing
    fun runNow(context: Context) {
        val request = OneTimeWorkRequestBuilder<NotesCleanupWorker>().build()
        WorkManager.getInstance(context).enqueue(request)
    }
}