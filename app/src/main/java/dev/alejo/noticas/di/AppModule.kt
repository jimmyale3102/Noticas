package dev.alejo.noticas.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.alejo.noticas.data.repository.RepositoryImpl
import dev.alejo.noticas.data.source.NoticasDatabase
import dev.alejo.noticas.domain.repository.Repository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNoticasDatabase(app: Application): NoticasDatabase {
        return Room.databaseBuilder(
            app,
            NoticasDatabase::class.java,
            NoticasDatabase.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideRepository(db: NoticasDatabase): Repository {
        return RepositoryImpl(db.noticasDao)
    }


}