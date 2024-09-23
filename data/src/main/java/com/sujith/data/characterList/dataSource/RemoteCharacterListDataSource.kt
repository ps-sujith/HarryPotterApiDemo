package com.sujith.data.characterList.dataSource

import com.sujith.data.characterList.dto.CharacterItemDto


interface RemoteCharacterListDataSource {
    suspend fun getAllCharacters(): List<CharacterItemDto>
}