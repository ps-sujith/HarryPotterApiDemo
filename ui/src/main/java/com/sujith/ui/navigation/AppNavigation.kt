package com.sujith.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.sujith.ui.feature_characterDetail.CharacterListDetailScreen
import com.sujith.ui.feature_characterList.CharacterListScreen
import com.sujith.ui.feature_characterList.viewmodel.CharacterListViewmodel
import com.sujith.ui.feature_splash.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HarryPotterApp) {
        navigation<HarryPotterApp>(startDestination = Splash) {
            composable<Splash> {
                SplashScreen(navController = navController)
            }
            composable<CharacterList> { entry ->
                val characterListVM =
                    entry.sharedViewModel<CharacterListViewmodel>(navController = navController)
                val characterListUiState by characterListVM.characterListUiState.collectAsStateWithLifecycle()
                CharacterListScreen(navController, characterListUiState)
            }
            composable<CharacterListDetail> { entry ->
                val characterListVM =
                    entry.sharedViewModel<CharacterListViewmodel>(navController = navController)
                val characterListUiState by characterListVM.characterListUiState.collectAsStateWithLifecycle()
                val characterId = entry.toRoute<CharacterListDetail>().characterID
                val characterDetails =
                    characterListUiState.characterList?.takeIf { it.isNotEmpty() }
                        ?.firstOrNull { it.id == characterId }
                CharacterListDetailScreen(characterDetails) {
                    navController.navigateUp()
                }
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
