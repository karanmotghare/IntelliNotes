package com.example.intellinotes.data.room

import androidx.room.TypeConverter

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