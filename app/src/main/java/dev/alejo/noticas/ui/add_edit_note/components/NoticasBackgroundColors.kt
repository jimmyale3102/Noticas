package dev.alejo.noticas.ui.add_edit_note.components

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.alejo.noticas.domain.model.Note

@Composable
fun NoticasBackgroundColors(
    selectedColor: Color,
    onColorSelected: (color: Int) -> Unit
) {
    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Note.noteColors.forEach { color ->
            val borderColor = if(color == selectedColor) {
                selectedColor
            } else {
                Color.Black
            }
            val animatedBorderColor by animateColorAsState(
                targetValue = borderColor,
                animationSpec = tween(durationMillis = 500),
                label = "border color"
            )
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .shadow(elevation = 8.dp, shape = CircleShape)
                    .clip(CircleShape)
                    .background(Color(color.toArgb()))
                    .border(4.dp, animatedBorderColor, CircleShape)
                    .clickable { onColorSelected(color.toArgb()) }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun NoticasBackgroundColorsPreview() {
    Box(Modifier
        .fillMaxSize()
        .padding(top = 64.dp)) {
        NoticasBackgroundColors(selectedColor = Color.White) { }
    }
}