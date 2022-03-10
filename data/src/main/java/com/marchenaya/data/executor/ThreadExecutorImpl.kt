package com.marchenaya.data.executor

import com.marchenaya.domain.executor.ThreadExecutor
import javax.inject.Inject

class ThreadExecutorImpl @Inject constructor() : ThreadExecutor {
    override fun execute(p0: Runnable) {
        Thread(p0).start()
    }
}