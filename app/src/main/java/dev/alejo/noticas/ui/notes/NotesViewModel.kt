package dev.alejo.noticas.ui.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.alejo.noticas.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesRepository: Repository
) : ViewModel() {

    private val _state = MutableStateFlow(NotesState())
    val state: StateFlow<NotesState> = _state

    private fun getNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.getAllNotes().collect { notes ->
                _state.value = _state.value.copy(notes = notes)
            }
        }
    }

    fun onEvent(notesEvent: NotesEvent) {
        when (notesEvent) {
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    notesRepository.deleteNote(notesEvent.note)
                }
            }

            is NotesEvent.GetAllNotes -> { getNotes() }

            is NotesEvent.SearchNote -> {
                viewModelScope.launch(Dispatchers.IO) {
                    notesRepository.getNotesBySearch(notesEvent.text.lowercase()).collect { notes ->
                        _state.value = _state.value.copy(notes = notes)
                    }
                }
            }
        }
    }

}