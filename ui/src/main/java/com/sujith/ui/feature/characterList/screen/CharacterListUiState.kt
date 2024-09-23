package com.sujith.ui.feature.characterList.screen

import com.sujith.domain.characterList.model.CharacterItem

data class CharacterListUiState(
    val isLoading: Boolean = false,
    val characterList: List<CharacterItem>? = null
)
