package com.sujith.ui.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sujith.ui.R


@Composable
fun RowTextView(title: String, content: String?) {
    Card(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_small)),
        elevation = CardDefaults.elevatedCardElevation(dimensionResource(id = (R.dimen.card_view_card_elevation))),
        colors = CardDefaults.cardColors(contentColor = MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = title,
                fontWeight = FontWeight.SemiBold,
                fontSize = dimensionResource(id = R.dimen.medium_font_size).value.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small)),
                text = content ?: "",
                fontWeight = FontWeight.SemiBold,
                fontSize = dimensionResource(id = R.dimen.small_font_size).value.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}
