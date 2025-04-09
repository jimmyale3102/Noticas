package dev.alejo.noticas.ui.notes

import dev.alejo.noticas.domain.model.Note

data class NotesState(
    val notes: List<Note> = emptyList()
)

const val CREATE_NOTE_FAB_KEY = "CREATE_NOTE_FAB_KEY"