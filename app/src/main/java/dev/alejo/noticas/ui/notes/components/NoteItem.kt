package dev.alejo.noticas.ui.notes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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

@Composable
fun NoteItem(note: Note) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color(note.color)
        ),
        shape = RoundedCornerShape(topStart = 18.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp)
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
                    Brush.linearGradient(
                        0.94f to Color(note.color).copy(1f),
                        0.95f to Color.Black.copy(0.02f),
                        1f to Color.White.copy(0.8f)
                    ),
                    shape = RectangleShape
                )
                .border(
                    width = 1.dp,
                    color = Color(note.color),
                    shape = RectangleShape
                )
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = note.title,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = note.content,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = note.timestamp.toString(),
                maxLines = 1,
                fontSize = 12.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NoteItemPreview() {
    Box(Modifier.padding(top = 70.dp, start = 16.dp, end = 16.dp)) {
        NoteItem(
            note = Note(
                title = "Shopping",
                content = "This is my list of shopping items This is my list of shopping items This is my list of shopping items This is my list of shopping items This is my list of shopping items This is my list of shopping items",
                timestamp = 0,
                color = Note.noteColors[3].toArgb()
            )
        )
    }
}