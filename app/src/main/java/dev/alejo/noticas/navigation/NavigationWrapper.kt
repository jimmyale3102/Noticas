package dev.alejo.noticas.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.alejo.noticas.ui.add_edit_note.AddEditNoteEvent
import dev.alejo.noticas.ui.add_edit_note.AddEditNoteScreen
import dev.alejo.noticas.ui.add_edit_note.AddEditNoteViewModel
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
                NotesScreen(
                    modifier = modifier,
                    state = state,
                    animatedVisibilityScope = this
                ) {
                    navController.navigate(Screens.AddEditNote)
                }
            }
            composable<Screens.AddEditNote> {
                val viewModel = hiltViewModel<AddEditNoteViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()
                AddEditNoteScreen(
                    modifier = modifier,
                    animatedVisibilityScope = this,
                    state = state,
                    onColorSelected = { color ->
                        viewModel.onEvent(AddEditNoteEvent.ChangeColor(color))
                    }
                )
            }
        }
    }
}