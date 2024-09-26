package com.sujith.ui.utils

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sujith.ui.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(scrollBehavior: TopAppBarScrollBehavior?) {
    TopAppBar(
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Gray
        ),
        title = {
            Text(
                text = stringResource(id = R.string.harry_potter),
                fontSize = dimensionResource(id = R.dimen.app_bar_title_size).value.sp,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onBackground
            )
        })
}