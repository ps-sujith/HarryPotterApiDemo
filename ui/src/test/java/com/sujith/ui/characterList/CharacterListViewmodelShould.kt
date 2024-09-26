package com.sujith.ui.characterList

import app.cash.turbine.test
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.sujith.domain.characterList.model.CharacterItem
import com.sujith.domain.characterList.usecase.GetCharacterListUseCase
import com.sujith.ui.feature_characterList.component.CharacterListUiState
import com.sujith.ui.feature_characterList.viewmodel.CharacterListViewmodel
import com.sujith.ui.utils.BaseUnitTest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class CharacterListViewmodelShould : BaseUnitTest() {

    private lateinit var viewmodel: CharacterListViewmodel
    private var getCharacterListUseCase: GetCharacterListUseCase = mock()
    private val characterList: List<CharacterItem> = mock()
    private val expected = CharacterListUiState(false, characterList)
    private val error = CharacterListUiState()

    @Test
    fun `fetch all character list from use case`() = runTest {
        mockSuccessfulCase()
        verify(getCharacterListUseCase, times(1)).getCharacterList()
    }

    @Test
    fun `emit character list  when success received from use case`() = runTest {
        mockSuccessfulCase()
        viewmodel.characterListUiState.test {
            assertEquals(expected, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `emit null list  when error received from use case `() = runTest {
        mockFailureCase()
        viewmodel.characterListUiState.test {
            assertEquals(error, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }

    }

    private suspend fun mockSuccessfulCase() {
        whenever(getCharacterListUseCase.getCharacterList()).thenReturn(
            flow { emit(Result.success(characterList)) }
        )
        viewmodel = CharacterListViewmodel(getCharacterListUseCase)
    }

    private suspend fun mockFailureCase() {
        whenever(getCharacterListUseCase.getCharacterList()).thenReturn(
            flow { emit(Result.failure(RuntimeException("something went wrong"))) }
        )
        viewmodel = CharacterListViewmodel(getCharacterListUseCase)
    }
}