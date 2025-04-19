package dev.alejo.noticas.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.alejo.noticas.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoticasDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)

    @Query("SELECT * FROM note WHERE id = :id")
    fun getNoteById(id: Int): Note?

    @Query("SELECT * FROM note ORDER BY timestamp DESC")
    fun getAllNotes(): Flow<List<Note>>

    @Delete
    fun deleteNote(note: Note)

    @Query("SELECT * FROM note WHERE LOWER(title) LIKE '%' || LOWER(:text) || '%' OR LOWER(content) LIKE '%' || LOWER(:text) || '%' ORDER BY timestamp DESC")
    fun getNotesBySearch(text: String): Flow<List<Note>>

}