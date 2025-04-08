package dev.alejo.noticas.domain.repository

import dev.alejo.noticas.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun saveNote(note: Note)
    suspend fun getNoteById(id: Int): Note?
    fun getAllNotes(): Flow<List<Note>>
    suspend fun deleteNote(note: Note)
}