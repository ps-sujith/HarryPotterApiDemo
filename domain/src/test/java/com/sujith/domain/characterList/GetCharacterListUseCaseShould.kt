package com.sujith.domain.characterList

import app.cash.turbine.test
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.sujith.domain.characterList.model.CharacterItem
import com.sujith.domain.characterList.repository.CharacterListRepository
import com.sujith.domain.characterList.usecase.GetCharacterListUseCase
import com.sujith.domain.utils.BaseUnitTest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetCharacterListUseCaseShould : BaseUnitTest() {
    private lateinit var getCharacterListUseCase: GetCharacterListUseCase
    private val characterListRepository: CharacterListRepository = mock()
    private val characterList: List<CharacterItem> = mock()
    private val expectedSuccess = Result.success(characterList)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun `fetch all the character list from repository`() = runTest {
        mockSuccessfulCase()
        getCharacterListUseCase.getCharacterList()
        verify(characterListRepository, times(1)).getAllCharacters()
    }

    @Test
    fun `emit success result when success received from repository`() = runTest {
        mockSuccessfulCase()
        getCharacterListUseCase.getCharacterList().test {
            assertEquals(expectedSuccess, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `emit error result  when error received from repository `() = runTest {
        mockFailureCase()
        getCharacterListUseCase.getCharacterList().test {
            assertEquals(exception, awaitItem().exceptionOrNull())
            cancelAndIgnoreRemainingEvents()
        }
    }

    private suspend fun mockSuccessfulCase() {
        whenever(characterListRepository.getAllCharacters()).thenReturn(
            flow { emit(expectedSuccess) }
        )
        getCharacterListUseCase = GetCharacterListUseCase(characterListRepository)
    }

    private suspend fun mockFailureCase() {
        whenever(characterListRepository.getAllCharacters()).thenReturn(
            flow { emit(Result.failure(exception)) }
        )
        getCharacterListUseCase = GetCharacterListUseCase(characterListRepository)

    }
}