package com.sujith.domain.characterList.usecase

import com.sujith.domain.characterList.model.CharacterItem
import com.sujith.domain.characterList.repository.CharacterListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterListUseCase @Inject constructor(private val characterListRepository: CharacterListRepository) {
    suspend fun getCharacterList(): Flow<Result<List<CharacterItem>>> {
        return characterListRepository.getAllCharacters()
    }
}