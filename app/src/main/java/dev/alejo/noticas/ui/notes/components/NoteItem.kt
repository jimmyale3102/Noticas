package dev.alejo.noticas.ui.notes.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.alejo.noticas.domain.model.Note
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun NoteItem(note: Note, onNoteClicked: (note: Note) -> Unit) {
    val dateFormatted = SimpleDateFormat(
        "dd MMM yyyy, HH:mm a",
        Locale.getDefault()
    ).format(Date(note.timestamp))

    val foldedGradient = if (isSystemInDarkTheme()) {
        Brush.linearGradient(
            0.9f to Color(note.color).copy(1f),
            0.91f to Color.White.copy(0.1f),
            1f to Color.Black.copy(0.6f)
        )
    } else {
        Brush.linearGradient(
            0.9f to Color(note.color).copy(1f),
            0.91f to Color.Black.copy(0.05f),
            1f to Color.White.copy(0.8f)
        )
    }
    Box(Modifier.padding(6.dp)) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onNoteClicked(note) },
            colors = CardDefaults.elevatedCardColors(
                containerColor = Color(note.color)
            ),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 8.dp
            ),
            shape = RoundedCornerShape(
                topStart = 18.dp,
                topEnd = 0.dp,
                bottomStart = 0.dp,
                bottomEnd = 0.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .background(
                        Brush.verticalGradient(
                            0f to Color.Black.copy(0.2f),
                            0.1f to Color.Black.copy(0.05f),
                            0.9f to Color.White.copy(0.05f),
                            1f to Color.White.copy(0.1f)
                        ),
                        shape = RectangleShape
                    )
                    .background(
                        foldedGradient,
                        shape = RectangleShape
                    )
                    .border(
                        width = 1.dp,
                        color = Color(note.color),
                        shape = RectangleShape
                    )
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = note.title,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = note.content,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Black
                )
                Text(
                    text = dateFormatted,
                    maxLines = 1,
                    fontSize = 12.sp,
                    color = Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun NoteItemPreview() {
    Box(Modifier.padding(top = 70.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        NoteItem(
            note = Note(
                title = "Shopping",
                content = "This is my list of shopping items This is my list of shopping items This is my list of shopping items This is my list of shopping items This is my list of shopping items This is my list of shopping items",
                timestamp = 0,
                color = Note.noteColors[3].toArgb()
            ),
            onNoteClicked = {}
        )
    }
}