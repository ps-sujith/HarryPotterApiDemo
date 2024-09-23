package com.sujith.data.characterList.repository

import com.sujith.data.characterList.dataSource.RemoteCharacterListDataSource
import com.sujith.data.characterList.dto.CharacterItemDto
import com.sujith.data.characterList.mapper.toDomain
import com.sujith.domain.characterList.model.CharacterItem
import com.sujith.domain.characterList.repository.CharacterListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterListRepositoryImpl @Inject constructor(private val remoteCharacterListDataSource: RemoteCharacterListDataSource) :
    CharacterListRepository {
    override suspend fun getAllCharacters(): Flow<Result<List<CharacterItem>>> {
        val result: List<CharacterItemDto> = remoteCharacterListDataSource.getAllCharacters()
        return flow {
            if (result.isNotEmpty()) {
                val mappedResult = result.map { it.toDomain() }
                emit(Result.success(mappedResult))
            } else {
                emit(Result.failure(RuntimeException("Something went wrong")))
            }
        }
    }
}