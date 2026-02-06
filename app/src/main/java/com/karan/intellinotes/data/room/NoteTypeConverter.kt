package com.karan.intellinotes.data.room

import androidx.room.TypeConverter
import com.karan.intellinotes.data.room.entity.NoteType

class NoteTypeConverter {

    @TypeConverter
    fun fromNoteType(type: NoteType): String {
        return type.name
    }

    @TypeConverter
    fun toNoteType(value: String): NoteType {
        return NoteType.valueOf(value)
    }

}