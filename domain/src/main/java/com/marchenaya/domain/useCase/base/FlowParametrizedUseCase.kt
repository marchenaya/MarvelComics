package com.marchenaya.domain.useCase.base

abstract class FlowParametrizedUseCase<T : Any, P> : UseCase() {

    fun launchUseCase(params: P) =
        build(params)

    protected abstract fun build(params: P): T
}