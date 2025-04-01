package com.kajal.jetnote.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kajal.jetnote.model.Note
import com.kajal.jetnote.util.DateConverter
import com.kajal.jetnote.util.UUIDConverter

@Database(entities = [Note::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class, DateConverter::class, UUIDConverter::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDatabaseDao
}