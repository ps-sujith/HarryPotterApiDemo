package com.sujith.ui.feature_characterDetail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.sujith.domain.characterList.model.CharacterItem
import com.sujith.ui.R
import com.sujith.ui.utils.ItemUtil
import com.sujith.ui.utils.RowTextView

@Composable
fun DetailsBottomView(characterItem: CharacterItem, houseColor: Color) {
    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.padding_large),
                    vertical = dimensionResource(id = R.dimen.padding_small)
                ),
            text = "OTHER DETAILS",
            fontWeight = FontWeight.Bold,
            fontSize = dimensionResource(id = R.dimen.large_font_size).value.sp,
            style = TextStyle(
                shadow = Shadow(
                    color = houseColor, offset = Offset(1f, 1f), blurRadius = 10f
                )
            ),
            textAlign = TextAlign.Center
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.padding_large),
                    vertical = dimensionResource(id = R.dimen.padding_small)
                ),
            elevation = CardDefaults.elevatedCardElevation(dimensionResource(id = (R.dimen.card_view_card_elevation))),

            ) {
            RowTextView(title = "DOB", content = characterItem.dateOfBirth)
            RowTextView(title = "Gender", content = characterItem.gender)
            RowTextView(title = "Species", content = characterItem.species)
            RowTextView(title = "Alive", content = characterItem.alive.toString())
            RowTextView(title = "Hair Colour", content = characterItem.hairColour)
            RowTextView(title = "Patronus", content = characterItem.patronus)
            RowTextView(title = "Eye Colour", content = characterItem.eyeColour)
            RowTextView(
                title = "Hogwarts Staff",
                content = characterItem.hogwartsStudent.toString()
            )
            RowTextView(
                title = "Hogwarts Student",
                content = characterItem.hogwartsStaff.toString()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsBottomPreview() {
    Surface {
        DetailsBottomView(
            ItemUtil.getDummyCharacterItem(),
            houseColor = Color.Red
        )
    }
}