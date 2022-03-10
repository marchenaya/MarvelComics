package com.marchenaya.data.di.module

import android.content.Context
import com.marchenaya.data.BuildConfig
import com.marchenaya.data.component.trace.TraceComponent
import com.marchenaya.data.component.trace.TraceComponentImpl
import com.marchenaya.data.executor.ThreadExecutorImpl
import com.marchenaya.data.manager.db.MarvelDatabase
import com.marchenaya.data.repository.ComicRepositoryImpl
import com.marchenaya.domain.executor.ThreadExecutor
import com.marchenaya.domain.repository.ComicRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
object DataModule {

    @Provides
    @Reusable
    fun traceComponent(): TraceComponent = TraceComponentImpl(BuildConfig.DEBUG)

    @Provides
    @Singleton
    fun marvelDatabase(context: Context): MarvelDatabase = MarvelDatabase.getDatabase(context)

    @Provides
    @Singleton
    fun comicRepository(comicRepositoryImpl: ComicRepositoryImpl): ComicRepository =
        comicRepositoryImpl

    @Provides
    @Singleton
    fun threadExecutor(threadExecutorImpl: ThreadExecutorImpl): ThreadExecutor =
        threadExecutorImpl

}
