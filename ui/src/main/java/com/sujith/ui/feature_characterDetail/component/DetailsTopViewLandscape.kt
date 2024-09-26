package com.sujith.ui.feature_characterDetail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sujith.domain.characterList.model.CharacterItem
import com.sujith.ui.R
import com.sujith.ui.utils.CoilImage
import com.sujith.ui.utils.ItemUtil

@Composable
fun DetailsTopViewLandscape(characterItem: CharacterItem, houseColor: Color) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height((configuration.screenHeightDp / 2).dp)
            .padding(
                horizontal = dimensionResource(id = R.dimen.padding_large),
                vertical = dimensionResource(id = R.dimen.padding_small)
            ),
        elevation = CardDefaults.elevatedCardElevation(dimensionResource(id = (R.dimen.card_view_card_elevation))),

        ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            houseColor, Color.Transparent
                        ),
                        tileMode = TileMode.Decal
                    )
                ),
            contentAlignment = Alignment.Center,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_medium)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CoilImage(
                    context = context, imageUri = characterItem.image, modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_large))
                        .clip(CircleShape)
                        .size(dimensionResource(id = R.dimen.details_image_landscape_size))
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = characterItem.name?.uppercase() ?: "",
                        fontSize = dimensionResource(id = R.dimen.large_font_size).value.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            shadow = Shadow(
                                color = houseColor, offset = Offset(10f, 10f), blurRadius = 10f
                            )
                        )
                    )

                    if (!characterItem.house.isNullOrEmpty()) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Sharp.Home,
                                tint = houseColor,
                                contentDescription = "House name"
                            )
                            Text(
                                text = characterItem.house!!,
                                fontSize = dimensionResource(id = R.dimen.small_font_size).value.sp,
                                color = MaterialTheme.colorScheme.onBackground,
                                fontStyle = FontStyle.Normal
                            )
                        }
                    }
                }

            }

        }
    }
}

@Preview(
    showBackground = true,
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
)
@Composable
fun DetailsTopViewLandscapePreview() {
    Surface {
        DetailsTopViewLandscape(
            characterItem = ItemUtil.getDummyCharacterItem(),
            houseColor = Color.Red
        )
    }
}