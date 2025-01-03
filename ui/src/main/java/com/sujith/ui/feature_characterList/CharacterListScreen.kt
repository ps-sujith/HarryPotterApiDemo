package com.sujith.ui.feature_characterList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sujith.ui.R
import com.sujith.ui.feature_characterList.component.CharacterListItemScreen
import com.sujith.ui.feature_characterList.component.CharacterListUiState
import com.sujith.ui.navigation.CharacterListDetail
import com.sujith.ui.utils.ErrorView
import com.sujith.ui.utils.Lottie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(
    navController: NavHostController,
    characterListUiState: CharacterListUiState,
    onSearchSelected: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                title = {
                    Text(
                        text = stringResource(id = R.string.harry_potter),
                        fontSize = dimensionResource(id = R.dimen.app_bar_title_size).value.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                actions = {
                    IconButton(onClick = { onSearchSelected() }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search",
                            tint = MaterialTheme.colorScheme.onBackground
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
            if (characterListUiState.isLoading) {
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
            } else {
                if (!characterListUiState.characterList.isNullOrEmpty()) {
                    LazyVerticalGrid(
                        modifier = Modifier.fillMaxSize(),
                        columns = GridCells.Fixed(2),
                        content = {
                            items(characterListUiState.characterList) { characterItem ->
                                CharacterListItemScreen(characterItem) { characterId ->
                                    navController.navigate(CharacterListDetail(characterId))
                                }
                            }
                        })
                } else {
                    ErrorView(error = "Something Went wrong !!")
                }
            }
        }
    }
}