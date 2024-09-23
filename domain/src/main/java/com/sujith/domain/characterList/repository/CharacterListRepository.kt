package com.sujith.domain.characterList.repository

import com.sujith.domain.characterList.model.CharacterItem
import kotlinx.coroutines.flow.Flow

interface CharacterListRepository {
    suspend fun getAllCharacters(): Flow<Result<List<CharacterItem>>>
}