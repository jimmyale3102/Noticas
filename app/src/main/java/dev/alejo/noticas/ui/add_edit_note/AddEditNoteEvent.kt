package dev.alejo.noticas.ui.add_edit_note

sealed class AddEditNoteEvent {
    data class ChangeColor(val color: Int) : AddEditNoteEvent()
    data object SaveNote : AddEditNoteEvent()
}