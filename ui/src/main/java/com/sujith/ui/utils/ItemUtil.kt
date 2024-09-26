package com.sujith.ui.utils

import com.sujith.domain.characterList.model.CharacterItem
import com.sujith.domain.characterList.model.Wand

object ItemUtil {
    fun getDummyCharacterItem() = CharacterItem(
        actor = "Daniel Radcliffe",
        alive = true,
        alternateActors = emptyList(),
        alternateNames = emptyList(),
        ancestry = "half-blood",
        dateOfBirth = "31-07-1980",
        eyeColour = "white",
        gender = "male",
        hairColour = "black",
        hogwartsStaff = false,
        hogwartsStudent = true,
        house = "Gryffindor",
        id = "1",
        image = "https://ik.imagekit.io/hpapi/harry.jpg",
        name = "Harry Potter",
        patronus = "stag",
        species = "human",
        wand = Wand("phoenix tail feather", 11.0, "holly"),
        wizard = true,
        yearOfBirth = 1980
    )
}