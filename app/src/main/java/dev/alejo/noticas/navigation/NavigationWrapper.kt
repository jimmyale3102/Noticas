package dev.alejo.noticas.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.alejo.noticas.ui.notes.NotesScreen

@Composable
fun NavigationWrapper(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(navController = navController, startDestination = Screens.Notes) {
        composable<Screens.Notes> {
            NotesScreen(superInnerPadding = innerPadding)
        }
        composable<Screens.AddEditNote> {

        }
    }
}