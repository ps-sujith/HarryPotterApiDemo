package com.sujith.data.characterList.api

import com.sujith.data.characterList.dto.CharacterItemDto
import retrofit2.http.GET

interface HarryPotterApiService {
    @GET("characters")
    suspend fun getAllCharacters(): List<CharacterItemDto>
}