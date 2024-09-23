package com.sujith.data.characterList

import app.cash.turbine.test
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.sujith.data.characterList.dto.CharacterItemDto
import com.sujith.data.characterList.dto.WandDto
import com.sujith.data.characterList.repository.CharacterListRepositoryImpl
import com.sujith.data.characterList.dataSource.RemoteCharacterListDataSource
import com.sujith.data.utils.BaseUnitTest
import com.sujith.domain.characterList.model.CharacterItem
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test


class CharacterListRepositoryShould : BaseUnitTest() {
    private lateinit var characterListRepository: CharacterListRepositoryImpl
    private var remoteCharacterListDataSource: RemoteCharacterListDataSource = mock()
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun `fetch all the character list from  remote data source`() = runTest {
        mockSuccessfulCase()
        characterListRepository.getAllCharacters()
        verify(remoteCharacterListDataSource, times(1)).getAllCharacters()
    }

    @Test
    fun `emit success result when success received from remote data source`() = runTest {
        mockSuccessfulCase()
        characterListRepository.getAllCharacters().test {
            assertEquals(1, awaitItem().getOrNull()?.size)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `emit error result  when error received from remote data source`() = runTest {
        mockFailureCase()
        characterListRepository.getAllCharacters().test {
            assertEquals(exception.message, awaitItem().exceptionOrNull()!!.message)
            cancelAndIgnoreRemainingEvents()
        }
    }

    private suspend fun mockSuccessfulCase() {
        val inputList = listOf(
            CharacterItemDto(
                "Daniel", true, emptyList(), emptyList(), "none", "00-00-0000", "red",
                "male", "red", false, true, "", "1", "", "harry", "NA", "human",
                WandDto("none", 11.00, "holly"), true, 2000
            )
        )
        whenever(remoteCharacterListDataSource.getAllCharacters()).thenReturn(inputList)
        characterListRepository = CharacterListRepositoryImpl(remoteCharacterListDataSource)
    }

    private suspend fun mockFailureCase() {
        whenever(remoteCharacterListDataSource.getAllCharacters()).thenReturn(emptyList())
        characterListRepository = CharacterListRepositoryImpl(remoteCharacterListDataSource)
    }
}