package dev.alejo.noticas.data.repository

import dev.alejo.noticas.data.source.NoticasDao
import dev.alejo.noticas.domain.model.Note
import dev.alejo.noticas.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val dao: NoticasDao
) : Repository {
    override suspend fun saveNote(note: Note) {
        dao.insertNote(note)
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    override fun getAllNotes(): Flow<List<Note>> {
        return dao.getAllNotes()
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }

}