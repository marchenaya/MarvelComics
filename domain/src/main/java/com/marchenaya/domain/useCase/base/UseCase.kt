package com.marchenaya.domain.useCase.base

import com.marchenaya.domain.executor.ThreadExecutor
import javax.inject.Inject
import kotlinx.coroutines.Job

abstract class UseCase {

    @Inject
    protected lateinit var threadExecutor: ThreadExecutor

    private var job: Job? = null

    fun endJob() {
        if (job?.isCompleted == false) {
            job?.cancel()
        }
    }

}
