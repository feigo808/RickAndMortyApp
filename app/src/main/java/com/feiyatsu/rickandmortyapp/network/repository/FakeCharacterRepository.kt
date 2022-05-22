package com.feiyatsu.rickandmortyapp.network.repository

import com.feiyatsu.rickandmortyapp.network.data.NetworkResource
import com.feiyatsu.rickandmortyapp.network.model.BaseInfo
import com.feiyatsu.rickandmortyapp.network.model.Character

class FakeCharacterRepository : CharactersRepositoryContract {

    override suspend fun getCharacters(): NetworkResource<List<Character>> {
        return NetworkResource.Success(
            mutableListOf<Character>(
                Character(
                    id = 1,
                    name = "Rick Sanchez",
                    status = "Alive",
                    species = "Human",
                    type = "",
                    gender = "Male",
                    origin = BaseInfo(
                        name = "Earth",
                        url = "https://rickandmortyapi.com/api/location/1"
                    ),
                    location = BaseInfo(
                        name = "Earth",
                        url = "https://rickandmortyapi.com/api/location/20"
                    ),
                    image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                    episodes = mutableListOf(),
                    url = "https://rickandmortyapi.com/api/character/1",
                    created = "2017-11-04T18:48:46.250Z"
                )
            )
        )
    }
}