package com.sujith.ui.feature_characterDetail.component

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import com.sujith.domain.characterList.model.CharacterItem

@Composable
fun DetailsTopView(characterItem: CharacterItem ,houseColor: Color) {
    val  configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    if (isLandscape) {
        DetailsTopViewLandscape(characterItem = characterItem, houseColor = houseColor)
    } else {
        DetailsTopViewPortrait(characterItem = characterItem, houseColor = houseColor)    }
}


