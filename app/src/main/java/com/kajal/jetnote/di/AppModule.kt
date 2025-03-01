package com.kajal.jetnote.di

import android.content.Context
import androidx.room.Room
import com.kajal.jetnote.data.NoteDatabase
import com.kajal.jetnote.data.NoteDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun providesNotesDao(noteDatabase: NoteDatabase): NoteDatabaseDao = noteDatabase.noteDao()

    @Singleton
    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context): NoteDatabase =
        Room.databaseBuilder(context, NoteDatabase::class.java, "notes_db")
            .fallbackToDestructiveMigration().build()
}