@file:OptIn(ExperimentalSharedTransitionApi::class)

package dev.alejo.noticas.ui.add_edit_note

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.alejo.noticas.ui.add_edit_note.components.NoticasBackgroundColors
import dev.alejo.noticas.ui.notes.CREATE_NOTE_FAB_KEY

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.AddEditNoteScreen(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    state: AddEditNoteState,
    onColorSelected: (color: Int) -> Unit,
    onTitleChange: (title: String) -> Unit,
    onContentChange: (content: String) -> Unit,
    onSaveNote: () -> Unit
) {

    val backgroundColor = rememberSaveable { mutableIntStateOf(state.backgroundColor.toArgb()) }
    val animatedBackground by animateColorAsState(
        targetValue = Color(backgroundColor.intValue),
        animationSpec = tween(durationMillis = 500),
        label = "background color"
    )

    LaunchedEffect(state.backgroundColor) {
        backgroundColor.intValue = state.backgroundColor.toArgb()
    }

    Scaffold(
        Modifier
            .sharedBounds(
                sharedContentState = rememberSharedContentState(key = CREATE_NOTE_FAB_KEY),
                animatedVisibilityScope = animatedVisibilityScope
            )
            .background(animatedBackground)
            .fillMaxSize()
            .imePadding(),
        topBar = {
            NoticasBackgroundColors(
                modifier.padding(vertical = 16.dp),
                selectedColor = state.backgroundColor
            ) { colorSelected ->
                onColorSelected(
                    colorSelected
                )
            }
        },
        floatingActionButton = {
            SmallFloatingActionButton(
                onClick = { onSaveNote() }
            ) {
                Icon(imageVector = Icons.Default.Check, contentDescription = null)
            }
        }
    ) { innerPadding ->
        Column(
            Modifier
                .background(animatedBackground)
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TextField(
                value = state.title,
                onValueChange = { onTitleChange(it) },
                placeholder = {
                    Text(
                        "Título",
                        color = DarkGray,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Transparent,
                    unfocusedContainerColor = Transparent,
                    disabledContainerColor = Transparent,
                    focusedIndicatorColor = Transparent,
                    unfocusedIndicatorColor = Transparent,
                ),
                singleLine = true,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    capitalization = KeyboardCapitalization.Sentences
                ),
                textStyle = TextStyle(
                    color = Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            )
            TextField(
                value = state.content,
                onValueChange = { onContentChange(it) },
                placeholder = {
                    Text(
                        "Descripción",
                        color = DarkGray,
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Transparent,
                    unfocusedContainerColor = Transparent,
                    disabledContainerColor = Transparent,
                    focusedIndicatorColor = Transparent,
                    unfocusedIndicatorColor = Transparent,
                ),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences
                ),
                textStyle = TextStyle(
                    color = Black
                ),
                singleLine = false
            )
        }
    }
}