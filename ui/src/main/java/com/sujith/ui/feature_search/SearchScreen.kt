package com.sujith.ui.feature_search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.sujith.domain.characterList.model.CharacterItem
import com.sujith.ui.R
import com.sujith.ui.feature_characterList.component.CharacterListItemScreen
import com.sujith.ui.feature_characterList.component.CharacterListUiState
import com.sujith.ui.feature_search.components.SearchField
import com.sujith.ui.navigation.CharacterListDetail
import com.sujith.ui.utils.ErrorView
import com.sujith.ui.utils.Lottie


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    characterListUiState: CharacterListUiState,
    navController: NavController,
) {
    var value by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                title = {
                    SearchField { query ->
                        value = query
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = stringResource(id = R.string.search_close_icon)
                        )
                    }
                })
        }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            if (!characterListUiState.characterList.isNullOrEmpty() && value.isNotEmpty()) {
                val searchResults: List<CharacterItem> = try {
                    characterListUiState.characterList.filter {
                        it.name!!.contains(value, ignoreCase = true)
                    }
                } catch (e: Exception) {
                    emptyList()
                }


                if (searchResults.isNotEmpty()) {
                    LazyVerticalGrid(
                        modifier = Modifier
                            .fillMaxSize(),
                        columns = GridCells.Fixed(2),
                        content = {
                            items(searchResults) { characterItem ->
                                CharacterListItemScreen(characterItem) { characterId ->
                                    navController.navigate(CharacterListDetail(characterId))
                                }
                            }
                        })
                } else {
                    ErrorView(error = stringResource(id = R.string.no_results))
                }
            } else {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    Lottie(
                        rawFile = R.raw.loader_anim,
                        isPlaying = true,
                        iterations = Int.MAX_VALUE,
                        modifier = Modifier.size(dimensionResource(id = R.dimen.loader_size_small))
                    )
                }
            }
        }
    }

}
