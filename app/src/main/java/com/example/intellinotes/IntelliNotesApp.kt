package com.example.intellinotes

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.example.intellinotes.data.worker.NotesCleanupScheduler
import com.example.intellinotes.data.worker.NotesSyncScheduler
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@HiltAndroidApp
class IntelliNotesApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()
        NotesCleanupScheduler.schedule(this)
        NotesSyncScheduler.schedule(this)

        val auth = FirebaseAuth.getInstance()
        if(auth.currentUser == null){
            auth.signInAnonymously()
        }

    }
}