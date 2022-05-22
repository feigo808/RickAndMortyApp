package com.feiyatsu.rickandmortyapp.network.model

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("info")
    val characterInfo: CharacterInfo,
    @SerializedName("results")
    val characters: List<Character>
)
