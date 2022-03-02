package com.marchenaya.data.mapper.base

abstract class DBMapper<K : Any, T : Any> {
    fun transformEntityList(input: List<T>): List<K> {
        return input.mapNotNull {
            try {
                transformEntityToDB(it)
            } catch (e: Exception) {
                onMappingError(e)
                null
            }
        }
    }

    fun transformDBEntityList(input: List<K>): List<T> {
        return input.mapNotNull {
            try {
                transformDBToEntity(it)
            } catch (e: Exception) {
                onMappingError(e)
                null
            }
        }
    }

    abstract fun transformDBToEntity(input: K): T
    abstract fun transformEntityToDB(input: T): K
    abstract fun onMappingError(error: Exception)
}