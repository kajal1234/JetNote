package com.kajal.jetnote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.kajal.jetnote.screens.NoteScreen
import com.kajal.jetnote.screens.NoteViewModel
import com.kajal.jetnote.ui.theme.JetNoteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetNoteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val notesViewModel: NoteViewModel by viewModels()
                    NotesApp(notesViewModel = notesViewModel)
                }
            }
        }
    }
}

@Composable
fun NotesApp(notesViewModel: NoteViewModel) {
    val notesList = notesViewModel.noteList.collectAsState().value
    NoteScreen(
        notes = notesList,
        onAddNote = { notesViewModel.addNote(it) },
        onRemoveNote = { notesViewModel.removeNote(it) }
    )
}