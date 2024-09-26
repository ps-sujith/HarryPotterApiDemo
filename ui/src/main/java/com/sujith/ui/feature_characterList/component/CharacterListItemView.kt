package com.sujith.ui.feature_characterList.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.sujith.domain.characterList.model.CharacterItem
import com.sujith.ui.R
import com.sujith.ui.theme.gryffindor
import com.sujith.ui.theme.hufflepuff
import com.sujith.ui.theme.ravenclaw
import com.sujith.ui.theme.slytherin
import com.sujith.ui.utils.CoilImage
import com.sujith.ui.utils.ItemUtil

@Composable
fun CharacterListItemScreen(characterItem: CharacterItem, onItemClick: (String) -> Unit) {
    val context = LocalContext.current
    val houseColor = when (characterItem.house) {
        HouseColor.Gryffindor.toString() -> gryffindor
        HouseColor.Slytherin.toString() -> slytherin
        HouseColor.Ravenclaw.toString() -> ravenclaw
        HouseColor.Hufflepuff.toString() -> hufflepuff
        else -> Color.Blue
    }
    Card(modifier = Modifier
        .fillMaxSize()
        .clickable { onItemClick(characterItem.id) }
        .padding(dimensionResource(id = R.dimen.padding_small)),
        elevation = CardDefaults.elevatedCardElevation(dimensionResource(id = (R.dimen.card_view_card_elevation))),
        colors = CardDefaults.cardColors(contentColor = MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.padding_small)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.grid_image_outer_size))
                    .clip(CircleShape)
                    .background(
                        color = houseColor
                    ),
                contentAlignment = Alignment.Center
            ) {
                CoilImage(
                    context = context, imageUri = characterItem.image, modifier = Modifier
                        .clip(CircleShape)
                        .size(dimensionResource(id = R.dimen.grid_image_inner_size))
                        .border(
                            width = dimensionResource(id = R.dimen.border_small),
                            color = MaterialTheme.colorScheme.surfaceContainer,
                            shape = CircleShape
                        )
                )
            }
            Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.padding_medium)))
            Text(
                text = characterItem.name ?: "",
                fontSize = dimensionResource(id = R.dimen.medium_font_size).value.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.padding_extra_small)))
            Text(
                text = characterItem.actor ?: "",
                fontSize = dimensionResource(id = R.dimen.small_font_size).value.sp,
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterListItemScreenPreview() {
    Surface {
        CharacterListItemScreen(
            characterItem = ItemUtil.getDummyCharacterItem(),
            onItemClick = {}
        )
    }
}
