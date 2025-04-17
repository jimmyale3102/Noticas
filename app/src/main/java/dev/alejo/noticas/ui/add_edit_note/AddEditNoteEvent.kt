package dev.alejo.noticas.ui.add_edit_note

sealed class AddEditNoteEvent {
    data class ChangeColor(val color: Int) : AddEditNoteEvent()
    data class ChangeTitle(val title: String) : AddEditNoteEvent()
    data class ChangeContent(val content: String) : AddEditNoteEvent()
    data class SetNoteById(val noteId: Int?) : AddEditNoteEvent()

    data object SaveNote : AddEditNoteEvent()
}