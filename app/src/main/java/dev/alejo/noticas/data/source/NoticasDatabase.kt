package dev.alejo.noticas.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.alejo.noticas.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoticasDatabase : RoomDatabase() {

    abstract val noticasDao: NoticasDao

    companion object {
        const val DATABASE_NAME = "noticas_db"
    }

}