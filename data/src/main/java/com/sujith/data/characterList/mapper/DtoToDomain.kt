package com.sujith.data.characterList.mapper

import com.sujith.data.characterList.dto.CharacterItemDto
import com.sujith.data.characterList.dto.WandDto
import com.sujith.domain.characterList.model.CharacterItem
import com.sujith.domain.characterList.model.Wand

fun CharacterItemDto.toDomain() = CharacterItem(
    actor = actor,
    alive = alive,
    alternateActors = alternateActors,
    alternateNames = alternateNames,
    ancestry = ancestry,
    dateOfBirth = dateOfBirth,
    eyeColour = eyeColour,
    gender = gender,
    hairColour = hairColour,
    hogwartsStaff = hogwartsStaff,
    hogwartsStudent = hogwartsStudent,
    house = house,
    id = id,
    image = image,
    name = name,
    patronus = patronus,
    species = species,
    wand = wand.toDomain(),
    wizard = wizard,
    yearOfBirth = yearOfBirth
)

fun WandDto.toDomain() = Wand(
    core = core,
    length = length,
    wood = wood
)