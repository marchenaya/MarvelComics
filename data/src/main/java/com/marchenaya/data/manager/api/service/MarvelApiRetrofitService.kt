package com.marchenaya.data.manager.api.service

import com.marchenaya.data.entity.remote.CharacterDataWrapperRemoteEntity
import com.marchenaya.data.entity.remote.ComicDataWrapperRemoteEntity
import com.marchenaya.data.entity.remote.CreatorDataWrapperRemoteEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MarvelApiRetrofitService {

    @GET("/v1/public/comics?orderBy=title")
    suspend fun getComics(
        @Query("offset") position: Int,
        @Query("limit") itemsPerPage: Int
    ): ComicDataWrapperRemoteEntity

    @GET("/v1/public/comics?orderBy=title")
    suspend fun getComicsByTitle(
        @Query("titleStartsWith") query: String,
        @Query("offset") position: Int,
        @Query("limit") itemsPerPage: Int
    ): ComicDataWrapperRemoteEntity

    @GET("/v1/public/comics/{comicId}/characters?orderBy=name")
    suspend fun getCharactersByComicId(
        @Path("comicId") comicId: Int
    ): CharacterDataWrapperRemoteEntity

    @GET("/v1/public/comics/{comicId}/creators?orderBy=lastName")
    suspend fun getCreatorsByComicId(
        @Path("comicId") comicId: Int
    ): CreatorDataWrapperRemoteEntity

    @GET("/v1/public/comics/{comicId}")
    suspend fun getComicById(
        @Path("comicId") comicId: Int
    ): ComicDataWrapperRemoteEntity


}