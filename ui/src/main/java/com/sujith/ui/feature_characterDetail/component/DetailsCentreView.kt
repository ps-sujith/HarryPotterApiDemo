package com.sujith.ui.feature_characterDetail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
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
import com.sujith.domain.characterList.model.Wand
import com.sujith.ui.R
import com.sujith.ui.utils.IconTextView

@Composable
fun DetailsCentreView(wand: Wand, houseColor: Color) {
    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.padding_large),
                    vertical = dimensionResource(id = R.dimen.padding_small)
                ),
            text = "WAND",
            fontWeight = FontWeight.Bold,
            fontSize = dimensionResource(id = R.dimen.large_font_size).value.sp,
            style = TextStyle(
                shadow = Shadow(
                    color = houseColor, offset = Offset(1f, 1f), blurRadius = 50f
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

            if (!wand.core.isNullOrEmpty()) {
                IconTextView(
                    icon = Icons.Filled.Star,
                    title = "Core",
                    content = wand.core!!,
                    houseColor = houseColor
                )
            }

            wand.length.takeIf { it > 0 }?.let {
                IconTextView(
                    icon = Icons.Filled.Create,
                    title = "Length",
                    content = it.toString(),
                    houseColor = houseColor
                )
            }

            if (!wand.wood.isNullOrEmpty()) {
                IconTextView(
                    icon = Icons.Filled.Warning,
                    title = "Wood",
                    content = wand.wood!!,
                    houseColor = houseColor
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsCentreViewPreview() {
    Surface {
        DetailsCentreView(
            Wand("phoenix tail feather", 11.0, "holly"),
            houseColor = Color.Red
        )
    }
}