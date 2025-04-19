package dev.alejo.noticas.ui.util

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.alejo.noticas.ui.theme.NoteShape
import kotlinx.coroutines.delay

@Composable
fun <T> SwipeToDeleteContainer(
    item: T,
    onDelete: (T) -> Unit,
    animationDuration: Int = 500,
    itemPadding: Dp = 0.dp,
    threshold: Float = 0.5f,
    content: @Composable (T) -> Unit
) {
    var isRemoved by rememberSaveable {
        mutableStateOf(false)
    }
    val haptic = LocalHapticFeedback.current
    var dismissBoxState: SwipeToDismissBoxState? = null
    dismissBoxState = rememberSwipeToDismissBoxState(
        positionalThreshold = { width -> width * threshold },
        confirmValueChange = { state ->
            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
            if (state == SwipeToDismissBoxValue.EndToStart && dismissBoxState!!.progress > threshold && !isRemoved) {
                isRemoved = true
                true
            } else {
                false
            }
        }
    )

    LaunchedEffect(key1 = isRemoved) {
        if (isRemoved) {
            delay(animationDuration.toLong())
            onDelete(item)
        }
    }

    AnimatedVisibility(
        visible = !isRemoved,
        exit = shrinkVertically(
            animationSpec = tween(durationMillis = animationDuration),
            shrinkTowards = Alignment.Top
        ) + fadeOut()
    ) {
        SwipeToDismissBox(
            state = dismissBoxState,
            backgroundContent = {
                DeleteBackground(swipeDismissState = dismissBoxState, itemPadding = itemPadding)
            }
        ) {
            content(item)
        }
    }
}

@Composable
fun DeleteBackground(
    swipeDismissState: SwipeToDismissBoxState,
    itemPadding: Dp
) {
    val color = if (swipeDismissState.dismissDirection == SwipeToDismissBoxValue.EndToStart) {
        Color.Red
    } else {
        Color.Transparent
    }

    Box(
        modifier = Modifier
            .padding(itemPadding)
            .fillMaxSize()
            .clip(NoteShape)
            .background(color)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = null,
            tint = Color.White
        )
    }
}