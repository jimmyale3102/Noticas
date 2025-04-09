package dev.alejo.noticas.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.alejo.noticas.ui.AddEditNoteScreen
import dev.alejo.noticas.ui.notes.NotesScreen
import dev.alejo.noticas.ui.notes.NotesViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavigationWrapper(navController: NavHostController, innerPadding: PaddingValues) {
    SharedTransitionLayout {
        NavHost(navController = navController, startDestination = Screens.Notes) {
            composable<Screens.Notes> {
                val viewModel = hiltViewModel<NotesViewModel>()
                val state = viewModel.state.value
                NotesScreen(
                    superInnerPadding = innerPadding,
                    state = state,
                    animatedVisibilityScope = this
                ) {
                    navController.navigate(Screens.AddEditNote)
                }
            }
            composable<Screens.AddEditNote> {
                AddEditNoteScreen(animatedVisibilityScope = this)
            }
        }
    }
}