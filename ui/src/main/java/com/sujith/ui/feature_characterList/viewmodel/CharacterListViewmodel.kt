package com.sujith.ui.feature_characterList.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sujith.domain.characterList.usecase.GetCharacterListUseCase
import com.sujith.ui.feature_characterList.component.CharacterListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewmodel @Inject constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase
) : ViewModel() {
    private val _characterListUiState = MutableStateFlow(CharacterListUiState())
    val characterListUiState = _characterListUiState.asStateFlow()

    init {
        loadCharacterList()
    }

    private fun loadCharacterList() {
        viewModelScope.launch {
            _characterListUiState.update {
                it.copy(isLoading = true)
            }
            getCharacterListUseCase.getCharacterList().collect { result ->
                _characterListUiState.update {
                    it.copy(isLoading = false, characterList = result.getOrNull())
                }
            }
        }
    }
}
