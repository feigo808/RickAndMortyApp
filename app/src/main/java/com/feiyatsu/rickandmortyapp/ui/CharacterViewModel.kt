package com.feiyatsu.rickandmortyapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feiyatsu.rickandmortyapp.network.data.NetworkResource
import com.feiyatsu.rickandmortyapp.network.model.Character
import com.feiyatsu.rickandmortyapp.network.repository.CharactersRepositoryContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: CharactersRepositoryContract
) : ViewModel() {

    private val _characters = MutableLiveData<NetworkResource<List<Character>>>()
    val characters: LiveData<NetworkResource<List<Character>>>
        get() = _characters

    fun fetchCharacters() {
        _characters.value = NetworkResource.Loading()
        viewModelScope.launch {
            val response = repository.getCharacters()
            _characters.postValue(response)
        }
    }
}