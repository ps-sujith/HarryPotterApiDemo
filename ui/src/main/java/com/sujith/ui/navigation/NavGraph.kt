package com.sujith.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object Splash

@Serializable
object CharacterList

@Serializable
data class CharacterListDetail(val characterID: String)

@Serializable
object HarryPotterApp