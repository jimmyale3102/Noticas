package dev.alejo.noticas.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.alejo.noticas.ui.notes.NotesScreen

@Composable
fun NavigationWrapper(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.Notes) {
        composable<Screens.Notes> {
            NotesScreen()
        }
        composable<Screens.AddEditNote> {

        }
    }
}