package com.sujith.data.characterList.dataSource

import com.sujith.data.characterList.api.HarryPotterApiService
import com.sujith.data.characterList.dto.CharacterItemDto
import javax.inject.Inject

class RemoteCharacterListDataSourceImpl @Inject constructor(private val apiService: HarryPotterApiService) :
    RemoteCharacterListDataSource {
    override suspend fun getAllCharacters(): List<CharacterItemDto> {
        return try {
            apiService.getAllCharacters().ifEmpty { emptyList() }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
