package com.sujith.data.characterList.dto


import com.google.gson.annotations.SerializedName

data class CharacterItemDto(
    @SerializedName("actor")
    val actor: String?,
    @SerializedName("alive")
    val alive: Boolean = false,
    @SerializedName("alternate_actors")
    val alternateActors: List<String> = emptyList(),
    @SerializedName("alternate_names")
    val alternateNames: List<String> = emptyList(),
    @SerializedName("ancestry")
    val ancestry: String?,
    @SerializedName("dateOfBirth")
    val dateOfBirth: String?,
    @SerializedName("eyeColour")
    val eyeColour: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("hairColour")
    val hairColour: String?,
    @SerializedName("hogwartsStaff")
    val hogwartsStaff: Boolean = false,
    @SerializedName("hogwartsStudent")
    val hogwartsStudent: Boolean = false,
    @SerializedName("house")
    val house: String?,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("patronus")
    val patronus: String?,
    @SerializedName("species")
    val species: String?,
    @SerializedName("wand")
    val wand: WandDto?,
    @SerializedName("wizard")
    val wizard: Boolean = false,
    @SerializedName("yearOfBirth")
    val yearOfBirth: Int = 0
)

data class WandDto(
    @SerializedName("core")
    val core: String?,
    @SerializedName("length")
    val length: Double = 0.0,
    @SerializedName("wood")
    val wood: String?
)