@file:OptIn(ExperimentalSharedTransitionApi::class)

package dev.alejo.noticas.ui.add_edit_note

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import dev.alejo.noticas.ui.add_edit_note.components.NoticasBackgroundColors
import dev.alejo.noticas.ui.notes.CREATE_NOTE_FAB_KEY

@Composable
fun SharedTransitionScope.AddEditNoteScreen(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    state: AddEditNoteState,
    onColorSelected: (color: Int) -> Unit
) {
    val animatedBackground by animateColorAsState(
        targetValue = state.backgroundColor,
        animationSpec = tween(durationMillis = 500),
        label = "background color"
    )
    Box(
        modifier
            .sharedBounds(
                sharedContentState = rememberSharedContentState(key = CREATE_NOTE_FAB_KEY),
                animatedVisibilityScope = animatedVisibilityScope
            )
            .background(animatedBackground)
            .fillMaxSize()
    ) {

        NoticasBackgroundColors(selectedColor = state.backgroundColor) { colorSelected -> onColorSelected(colorSelected)}
        Button(modifier = Modifier.padding(top = 64.dp), onClick = { onColorSelected(Black.toArgb()) }) {
            Text("Add new note")
        }
    }
}
