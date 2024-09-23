package com.sujith.data.characterList

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.sujith.data.characterList.api.HarryPotterApiService
import com.sujith.data.characterList.dto.CharacterItemDto
import com.sujith.data.characterList.dataSource.RemoteCharacterListDataSourceImpl
import com.sujith.data.utils.BaseUnitTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class RemoteCharacterListDataSourceShould : BaseUnitTest() {
    private lateinit var remoteCharacterListDataSource: RemoteCharacterListDataSourceImpl
    private val apiService: HarryPotterApiService = mock()
    private val expectedSuccess: List<CharacterItemDto> = mock()

    @Test
    fun `fetch all the character list from api service`() = runTest {
        mockSuccessfulCase()
        apiService.getAllCharacters()
        verify(apiService, times(1)).getAllCharacters()
    }

    @Test
    fun `return character list when when api is success`() = runTest {
        mockSuccessfulCase()
        assertEquals(expectedSuccess, remoteCharacterListDataSource.getAllCharacters())
    }

    @Test
    fun `return exception  when api fails `() = runTest {
        mockFailureCase()
        assertEquals(null, remoteCharacterListDataSource.getAllCharacters())
    }

    private suspend fun mockSuccessfulCase() {
        whenever(apiService.getAllCharacters()).thenReturn(expectedSuccess)
        remoteCharacterListDataSource = RemoteCharacterListDataSourceImpl(apiService)

    }

    private suspend fun mockFailureCase() {
        whenever(apiService.getAllCharacters()).thenReturn(null)
        remoteCharacterListDataSource = RemoteCharacterListDataSourceImpl(apiService)
    }
}