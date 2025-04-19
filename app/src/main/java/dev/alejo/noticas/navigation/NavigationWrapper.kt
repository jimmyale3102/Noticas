package dev.alejo.noticas.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import dev.alejo.noticas.ui.add_edit_note.AddEditNoteEvent
import dev.alejo.noticas.ui.add_edit_note.AddEditNoteScreen
import dev.alejo.noticas.ui.add_edit_note.AddEditNoteViewModel
import dev.alejo.noticas.ui.notes.NotesEvent
import dev.alejo.noticas.ui.notes.NotesScreen
import dev.alejo.noticas.ui.notes.NotesViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavigationWrapper(navController: NavHostController, modifier: Modifier) {
    SharedTransitionLayout {
        NavHost(navController = navController, startDestination = Screens.Notes) {
            composable<Screens.Notes> {
                val viewModel = hiltViewModel<NotesViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()

                LaunchedEffect(Unit) {
                    viewModel.onEvent(NotesEvent.GetAllNotes)
                }

                NotesScreen(
                    modifier = modifier,
                    state = state,
                    animatedVisibilityScope = this,
                    onNoteSelected = { noteSelected ->
                        navController.navigate(Screens.AddEditNote(noteSelected.id))
                    },
                    onCreateNote = { navController.navigate(Screens.AddEditNote()) },
                    onDelete = { note ->
                        viewModel.onEvent(NotesEvent.DeleteNote(note))
                    },
                    onSearchNote = { text ->
                        viewModel.onEvent(NotesEvent.SearchNote(text))
                    },
                    onCancelSearch = {
                        viewModel.onEvent(NotesEvent.GetAllNotes)
                    }
                )
            }
            composable<Screens.AddEditNote> {
                val args = it.toRoute<Screens.AddEditNote>()
                val viewModel = hiltViewModel<AddEditNoteViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()

                LaunchedEffect(args.noteId) {
                    if (args.noteId != null) {
                        viewModel.onEvent(AddEditNoteEvent.SetNoteById(args.noteId))
                    }
                }

                AddEditNoteScreen(
                    modifier = modifier,
                    animatedVisibilityScope = this,
                    state = state,
                    onColorSelected = { color ->
                        viewModel.onEvent(AddEditNoteEvent.ChangeColor(color))
                    },
                    onTitleChange = { title ->
                        viewModel.onEvent(AddEditNoteEvent.ChangeTitle(title))
                    },
                    onContentChange = { content ->
                        viewModel.onEvent(AddEditNoteEvent.ChangeContent(content))
                    },
                    onSaveNote = {
                        viewModel.onEvent(AddEditNoteEvent.SaveNote)
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}