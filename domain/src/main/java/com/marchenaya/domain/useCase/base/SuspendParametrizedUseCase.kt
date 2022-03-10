package com.marchenaya.domain.useCase.base

abstract class SuspendParametrizedUseCase<T : Any, P> : UseCase() {

    suspend fun launchUseCase(params: P) =
        build(params)

    protected abstract suspend fun build(params: P): T
}