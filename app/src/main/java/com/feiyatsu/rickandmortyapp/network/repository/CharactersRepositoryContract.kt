package com.feiyatsu.rickandmortyapp.network.repository

import com.feiyatsu.rickandmortyapp.network.data.NetworkResource
import com.feiyatsu.rickandmortyapp.network.model.Character

interface CharactersRepositoryContract {
    suspend fun getCharacters(): NetworkResource<List<Character>>
}