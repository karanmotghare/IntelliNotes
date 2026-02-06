package com.karan.intellinotes.domain.usecases

import com.karan.intellinotes.core.utils.startOfDay
import com.karan.intellinotes.ui.notes.NoteUiModel
import com.karan.intellinotes.ui.notes.NotesSection
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class GroupNotesByDateUseCase @Inject constructor(){

    operator fun invoke(notes: List<NoteUiModel>): List<NotesSection> {
        if (notes.isEmpty()) return emptyList()
        val todayStart = startOfDay(System.currentTimeMillis())
        val yesterdayStart = todayStart - TimeUnit.DAYS.toMillis(1)

        val grouped = notes
            .sortedByDescending { it.updatedAt }
            .groupBy { note ->
                when {
                    note.updatedAt >= todayStart -> Section.TODAY
                    note.updatedAt >= yesterdayStart -> Section.YESTERDAY
                    else -> Section.EARLIER
                }
            }

        return buildList {
            Section.entries.forEach { section ->
                val sectionNotes = grouped[section].orEmpty()
                if (sectionNotes.isNotEmpty()) {
                    add(
                        NotesSection(
                            title = section.title,
                            notes = sectionNotes
                        )
                    )
                }
            }
        }
    }
}

private enum class Section(val title: String) {
    TODAY("Today"),
    YESTERDAY("Yesterday"),
    EARLIER("Earlier")
}