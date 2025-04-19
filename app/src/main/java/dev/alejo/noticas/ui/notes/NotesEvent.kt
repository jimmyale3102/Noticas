package dev.alejo.noticas.ui.notes

import dev.alejo.noticas.domain.model.Note

sealed class NotesEvent {
    data object GetAllNotes : NotesEvent()
    data class DeleteNote(val note: Note) : NotesEvent()
    data class SearchNote(val text: String) : NotesEvent()
}