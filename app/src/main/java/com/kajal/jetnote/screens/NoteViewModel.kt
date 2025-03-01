package com.kajal.jetnote.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kajal.jetnote.model.Note
import com.kajal.jetnote.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {
    //private var noteList = mutableStateListOf<Note>()
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        //noteList.addAll(NotesDataSource().loadNotes())
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().collect { listOfNotes ->
                if (listOfNotes.isNullOrEmpty().not()) {
                    _noteList.value = listOfNotes
                }
            }
        }
    }

    fun addNote(note: Note) = viewModelScope.launch {
        repository.addNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        repository.updateNote(note)
    }

    fun removeNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }
}