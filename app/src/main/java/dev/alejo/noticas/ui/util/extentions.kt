package dev.alejo.noticas.ui.util

import androidx.compose.ui.graphics.Color

fun Color.adjustForTheme(isDarkTheme: Boolean): Color =
    if (isDarkTheme) this.copy(alpha = 0.8f) else this