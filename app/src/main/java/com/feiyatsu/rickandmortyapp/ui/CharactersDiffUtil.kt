package com.feiyatsu.rickandmortyapp.ui

import androidx.recyclerview.widget.DiffUtil
import com.feiyatsu.rickandmortyapp.network.model.Character

class CharactersDiffUtil(
    private val oldList: List<Character>,
    private val newList: List<Character>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}