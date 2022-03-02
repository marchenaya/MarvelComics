package com.marchenaya.data.mapper.base

abstract class RemoteMapper<K : Any?, T : Any?> {
    fun transformEntityList(input: List<T>): List<K> {
        return input.mapNotNull {
            try {
                transformEntityToRemote(it)
            } catch (e: Exception) {
                onMappingError(e)
                null
            }
        }
    }

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