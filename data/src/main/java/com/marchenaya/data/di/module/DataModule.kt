package com.marchenaya.data.di.module

import com.marchenaya.data.BuildConfig
import com.marchenaya.data.component.trace.TraceComponent
import com.marchenaya.data.component.trace.TraceComponentImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object DataModule {

    @Provides
    @Reusable
    fun traceComponent(): TraceComponent = TraceComponentImpl(BuildConfig.DEBUG)

}
