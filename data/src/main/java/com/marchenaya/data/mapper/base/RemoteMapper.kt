package com.marchenaya.data.mapper.base

abstract class RemoteMapper<K : Any?, T : Any?> {

    fun transformRemoteEntityList(input: List<K>): List<T> {
        return input.mapNotNull {
            try {
                transformRemoteToEntity(it)
            } catch (e: Exception) {
                onMappingError(e)
                null
            }
        }
    }

    abstract fun transformEntityToRemote(input: T): K
    abstract fun transformRemoteToEntity(input: K): T
    abstract fun onMappingError(error: Exception)
}