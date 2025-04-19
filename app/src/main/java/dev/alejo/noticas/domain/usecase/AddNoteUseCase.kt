package dev.alejo.noticas.domain.usecase

import dev.alejo.noticas.domain.model.Note
import dev.alejo.noticas.domain.repository.Repository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(note: Note) {
        repository.saveNote(note)
    }
}