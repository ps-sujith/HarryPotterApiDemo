package com.sujith.ui.feature_characterDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.sujith.domain.characterList.model.CharacterItem
import com.sujith.ui.R
import com.sujith.ui.feature_characterDetail.component.DetailsBottomView
import com.sujith.ui.feature_characterDetail.component.DetailsCentreView
import com.sujith.ui.feature_characterDetail.component.DetailsTopView
import com.sujith.ui.feature_characterList.component.HouseColor
import com.sujith.ui.theme.gryffindor
import com.sujith.ui.theme.hufflepuff
import com.sujith.ui.theme.ravenclaw
import com.sujith.ui.theme.slytherin
import com.sujith.ui.utils.ErrorView
import com.sujith.ui.utils.ItemUtil

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListDetailScreen(characterItem: CharacterItem?, onBackClick: () -> Unit) {
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
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "BACK"
                        )
                    }
                })
        }) { innerPadding ->
        if (characterItem != null) {
            val houseColor = when (characterItem.house) {
                HouseColor.Gryffindor.toString() -> gryffindor
                HouseColor.Slytherin.toString() -> slytherin
                HouseColor.Ravenclaw.toString() -> ravenclaw
                HouseColor.Hufflepuff.toString() -> hufflepuff
                else -> Color.Gray
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
            ) {
                DetailsTopView(characterItem = characterItem, houseColor = houseColor)
                characterItem.wand?.let {
                    if (!it.wood.isNullOrEmpty() || !it.core.isNullOrEmpty() || it.length > 0) {
                        DetailsCentreView(wand = it, houseColor = houseColor)
                    }
                }
                DetailsBottomView(characterItem = characterItem, houseColor = houseColor)
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center

            ) {
                ErrorView(error = "No details found")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterListDetailPreview() {
    Surface {
        CharacterListDetailScreen(
            characterItem =
            ItemUtil.getDummyCharacterItem(),
            onBackClick = {}
        )
    }
}