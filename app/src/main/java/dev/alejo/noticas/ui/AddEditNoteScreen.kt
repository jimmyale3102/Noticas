@file:OptIn(ExperimentalSharedTransitionApi::class)

package dev.alejo.noticas.ui

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.alejo.noticas.ui.notes.CREATE_NOTE_FAB_KEY

@Composable
fun SharedTransitionScope.AddEditNoteScreen(
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Box(
        Modifier
            .sharedBounds(
                sharedContentState = rememberSharedContentState(key = CREATE_NOTE_FAB_KEY),
                animatedVisibilityScope = animatedVisibilityScope
            )
            .fillMaxSize()
    ) {
        Text("Add new note")
    }
}