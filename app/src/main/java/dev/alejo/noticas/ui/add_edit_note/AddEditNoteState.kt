package dev.alejo.noticas.ui.add_edit_note

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import dev.alejo.noticas.domain.model.Note

data class AddEditNoteState(
    val id: Int? = null,
    val title: String = "",
    val content: String = "",
    val backgroundColor: Color = Color(Note.noteColors.random().toArgb())
)