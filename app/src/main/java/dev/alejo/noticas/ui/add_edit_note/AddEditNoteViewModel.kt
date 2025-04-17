package dev.alejo.noticas.ui.add_edit_note

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.alejo.noticas.domain.model.Note
import dev.alejo.noticas.domain.usecase.AddNoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AddEditNoteState())
    val state : StateFlow<AddEditNoteState> = _state

    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.ChangeColor -> {
                _state.update { state ->
                    state.copy(backgroundColor = Color(event.color))
                }
            }
            AddEditNoteEvent.SaveNote -> {
                viewModelScope.launch(Dispatchers.IO) {
                    addNoteUseCase(
                        Note(
                            title = state.value.title,
                            content = state.value.content,
                            color = state.value.backgroundColor.toArgb(),
                            timestamp = System.currentTimeMillis()
                        )
                    )
                }
            }

            is AddEditNoteEvent.ChangeTitle -> {
                _state.update { state ->
                    state.copy(title = event.title)
                }
            }

            is AddEditNoteEvent.ChangeContent -> {
                _state.update { state ->
                    state.copy(content = event.content)
                }
            }
        }

    }

}