package dev.alejo.noticas.ui.notes

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.alejo.noticas.R
import dev.alejo.noticas.domain.model.Note
import dev.alejo.noticas.ui.notes.components.NoteItem

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.NotesScreen(
    modifier: Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    state: NotesState,
    onEvent: (NotesEvent) -> Unit = {},
    onCreateNote: () -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { NotesAppBar() },
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
            if (notesData == null) {
                Box(Modifier.fillMaxSize().padding(innerPadding)) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            } else {
                if (notesData.isEmpty()) {
                    EmptyNotesContent(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                } else {
                    NotesContent(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        notes = notesData
                    )
                }
            }
        }
    }
}

@Composable
fun EmptyNotesContent(modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        Image(
            painter = painterResource(R.drawable.empty_notes),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 64.dp)
        )
        Text(text = "No hay notas")
    }
}

@Composable
fun NotesContent(modifier: Modifier, notes: List<Note>) {
    LazyVerticalGrid(
        modifier = modifier.padding(horizontal = 16.dp),
        columns = GridCells.Fixed(2)
    ) {
        items(notes.size) { index ->
            NoteItem(notes[index]) {}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesAppBar() {
    Row(
        Modifier
            .fillMaxWidth()
            .height(TopAppBarDefaults.MediumAppBarCollapsedHeight)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        SmallFloatingActionButton(
            onClick = { },
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer
        ) {
            Icon(imageVector = Icons.Filled.Search, contentDescription = null)
        }
        SmallFloatingActionButton(
            modifier = Modifier.padding(start = 16.dp),
            onClick = { },
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer
        ) {
            Icon(imageVector = Icons.Filled.Info, contentDescription = null)
        }
    }
}

@Preview
@Composable
fun NotesAppBarPreview() {
//    NotesScreen(
//        state = NotesState(
//            notes = listOf(
//                Note(
//                    title = "Hey youuu",
//                    content = "Content",
//                    timestamp = 0,
//                    color = Note.noteColors[1].toArgb()
//                ),
//                Note(
//                    title = "Title",
//                    content = "Content",
//                    timestamp = 0,
//                    color = Note.noteColors[3].toArgb()
//                ),
//                Note(
//                    title = "Proo",
//                    content = "Content",
//                    timestamp = 0,
//                    color = Note.noteColors[2].toArgb()
//                ),
//                Note(
//                    title = "Title",
//                    content = "Content",
//                    timestamp = 0,
//                    color = Note.noteColors[0].toArgb()
//                )
//            )
//        ),
//        onEvent = {},
//        onCreateNote = {}
//    )
}
