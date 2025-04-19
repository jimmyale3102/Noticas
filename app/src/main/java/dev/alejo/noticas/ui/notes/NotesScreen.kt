package dev.alejo.noticas.ui.notes

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import dev.alejo.noticas.R
import dev.alejo.noticas.domain.model.Note
import dev.alejo.noticas.ui.notes.components.EmptyNotesContent
import dev.alejo.noticas.ui.notes.components.NoteItem
import dev.alejo.noticas.ui.notes.components.NotesAppBar
import dev.alejo.noticas.ui.util.SwipeToDeleteContainer

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.NotesScreen(
    modifier: Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    state: NotesState,
    onNoteSelected: (noteSelected: Note) -> Unit,
    onCreateNote: () -> Unit,
    onDelete: (note: Note) -> Unit,
    onSearchNote: (text: String) -> Unit,
    onCancelSearch: () -> Unit
) {
    var searchBarText by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            NotesAppBar(
                searchBarText = searchBarText,
                onSearchBarChange = { text -> searchBarText = text },
                onSearchNote = { onSearchNote(searchBarText) },
                onCancelSearch = {
                    searchBarText = ""
                    onCancelSearch()
                    focusManager.clearFocus()
                    keyboardController?.hide()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onCreateNote() },
                modifier = Modifier
                    .sharedBounds(
                        sharedContentState = rememberSharedContentState(
                            key = CREATE_NOTE_FAB_KEY
                        ),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        }
    ) { innerPadding ->
        AnimatedContent(state.notes) { notesData ->
            when {
                notesData == null -> {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                }

                notesData.isNotEmpty() -> {
                    NotesContent(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        notes = notesData,
                        onNoteSelected = onNoteSelected,
                        onDelete = { note -> onDelete(note) }
                    )
                }

                searchBarText.isEmpty() && notesData.isEmpty() -> {
                    EmptyNotesContent(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        imageResource = R.drawable.empty_notes,
                        description = "No hay notas"
                    )
                }

                searchBarText.isNotEmpty() && notesData.isEmpty() -> {
                    EmptyNotesContent(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        imageResource = R.drawable.empty_search,
                        description = "No se encontraron resultados para '$searchBarText'"
                    )
                }
            }
        }
    }
}

@Composable
fun NotesContent(
    modifier: Modifier,
    notes: List<Note>,
    onNoteSelected: (noteSelected: Note) -> Unit,
    onDelete: (note: Note) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(
            count = notes.size,
            key = { it }
        ) { index ->
            SwipeToDeleteContainer(
                item = notes[index],
                onDelete = { note -> onDelete(note) },
                itemPadding = 8.dp
            ) {
                NoteItem(notes[index]) { noteSelected ->
                    onNoteSelected(noteSelected)
                }
            }
        }
    }
}