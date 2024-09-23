package com.sujith.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.sujith.ui.feature.characterList.screen.CharacterListScreen
import com.sujith.ui.feature.characterList.viewmodel.CharacterListViewmodel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = CharacterListInfo) {
        navigation<CharacterListInfo>(startDestination = CharacterList) {


            composable<CharacterList> { entry ->
                val characterListVM =
                    entry.sharedViewModel<CharacterListViewmodel>(navController = navController)
                CharacterListScreen()
            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController,
): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}
