package com.marchenaya.data.mapper.base

abstract class ModelMapper<K : Any, T : Any> {
    fun transformEntityList(input: List<T>): List<K> {
        return input.mapNotNull {
            try {
                transformEntityToModel(it)
            } catch (e: Exception) {
                onMappingError(e)
                null
            }
        }
    }

    fun transformModelList(input: List<K>): List<T> {
        return input.mapNotNull {
            try {
                transformModelToEntity(it)
            } catch (e: Exception) {
                onMappingError(e)
                null
            }
        }
    }

    abstract fun transformModelToEntity(input: K): T
    abstract fun transformEntityToModel(input: T): K
    abstract fun onMappingError(error: Exception)
}